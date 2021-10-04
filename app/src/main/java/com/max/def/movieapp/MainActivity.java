package com.max.def.movieapp;

import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.max.def.movieapp.Adpaters.MovieSearchAdapter;
import com.max.def.movieapp.Adpaters.PersonSearchAdapter;
import com.max.def.movieapp.Client.RetrofitClient;
import com.max.def.movieapp.Interfaces.RetrofitService;
import com.max.def.movieapp.Model.MovieResponse;
import com.max.def.movieapp.Model.MovieResponseResults;
import com.max.def.movieapp.Model.PersonResponse;
import com.max.def.movieapp.Model.PersonResponseResults;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private NiceSpinner sourceSpinner;

    private AppCompatEditText queryEditText;

    private AppCompatButton querySearchButton;

    private RecyclerView resultsRecyclerView;

    private String movie = "By Movie Title";
    private String person = "By Person Name";

    // initiate the retrofit service

    private RetrofitService retrofitService;

    private MovieSearchAdapter movieSearchAdapter;
    private PersonSearchAdapter personSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // disable the keyword on start

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        sourceSpinner = findViewById(R.id.source_spinner);

        queryEditText = findViewById(R.id.query_edit_text);

        querySearchButton = findViewById(R.id.query_search_button);

        resultsRecyclerView = findViewById(R.id.results_recycler_view);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        Paper.init(this);

        retrofitService = RetrofitClient.getClient().create(RetrofitService.class);

        final ArrayList<String> category = new ArrayList<>();

        // set list for sourceSpinner

        // person name means actors

        category.add(movie);
        category.add(person);

        sourceSpinner.attachDataSource(category);

        //retrieve the position at start and the set the spinner

        if (Paper.book().read("position") != null)
        {
            int position = Paper.book().read("position");

            sourceSpinner.setSelectedIndex(position);
        }

        //set the text on edit text on create

        int position = sourceSpinner.getSelectedIndex();

        if (position == 0)
        {
            queryEditText.setHint("Enter any movie title...");
        }
        else
        {
            queryEditText.setHint("Enter any person name...");
        }

        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // when sourceSpinner in clicked change the text of  the edit text

                if (position == 0)
                {
                    queryEditText.setHint("Enter any movie title...");
                }
                else
                {
                    queryEditText.setHint("Enter any person name...");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        //retrieve the results from paper db and start

        if(Paper.book().read("cache") != null)
        {
            String results = Paper.book().read("cache");

            if (Paper.book().read("source") != null)
            {
                String source = Paper.book().read("source");

                if (source.equals("movie"))
                {
                    // convert the string cache to model movie response class using gson

                    MovieResponse movieResponse = new Gson().fromJson(results, MovieResponse.class);

                    if (movieResponse != null)
                    {
                        List<MovieResponseResults> movieResponseResults = movieResponse.getResults();

                        movieSearchAdapter = new MovieSearchAdapter(MainActivity.this,movieResponseResults);

                        resultsRecyclerView.setAdapter(movieSearchAdapter);

                        // create some animation to recycler view item loading

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this,R.anim.layout_slide_right);

                        resultsRecyclerView.setLayoutAnimation(controller);
                        resultsRecyclerView.scheduleLayoutAnimation();

                        // now store the results in paper database to access offline

                        Paper.book().write("cache",new Gson().toJson(movieResponse));

                        // store also the category to set the spinner at app start

                        Paper.book().write("source","movie");

                    }
                }
                else
                {
                    PersonResponse personResponse = new Gson().fromJson(results, PersonResponse.class);

                    if (personResponse != null)
                    {
                        List<PersonResponseResults> personResponseResults = personResponse.getResults();

                        personSearchAdapter = new PersonSearchAdapter(MainActivity.this,personResponseResults);

                        resultsRecyclerView.setAdapter(personSearchAdapter);

                        // create some animation to recycler view item loading

                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this,R.anim.layout_slide_right);

                        resultsRecyclerView.setLayoutAnimation(controller);
                        resultsRecyclerView.scheduleLayoutAnimation();

                        // now store the results in paper database to access offline

                        Paper.book().write("cache",new Gson().toJson(personResponse));

                        // store also the category to set the spinner at app start

                        Paper.book().write("source","person");

                    }
                }
            }
        }


        //get the query from user

        querySearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (queryEditText.getText() != null)
                {
                    String query = queryEditText.getText().toString();

                    if (query.equals("") || query.equals(" "))
                    {
                        Toast.makeText(MainActivity.this, "Please enter any text...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        queryEditText.setText("");

                        //get the category to search the query.    movie or person

                        String finalQuery = query.replaceAll(" ","+");

                        if (category.size() > 0)
                        {
                            String categoryName = category.get(sourceSpinner.getSelectedIndex());

                            if (categoryName.equals(movie))
                            {
                                Call<MovieResponse> movieResponseCall = retrofitService.getMoviesByQuery(BuildConfig.THE_MOVIE_DB_API_KEY,finalQuery);

                                movieResponseCall.enqueue(new Callback<MovieResponse>()
                                {
                                    @Override
                                    public void onResponse(@NonNull Call<MovieResponse> call,@NonNull Response<MovieResponse> response)
                                    {
                                        MovieResponse movieResponse = response.body();

                                        if (movieResponse != null)
                                        {
                                            List<MovieResponseResults> movieResponseResults = movieResponse.getResults();

                                            movieSearchAdapter = new MovieSearchAdapter(MainActivity.this,movieResponseResults);

                                            resultsRecyclerView.setAdapter(movieSearchAdapter);

                                            // create some animation to recycler view item loading

                                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this,R.anim.layout_slide_right);

                                            resultsRecyclerView.setLayoutAnimation(controller);
                                            resultsRecyclerView.scheduleLayoutAnimation();

                                            // now store the results in paper database to access offline

                                            Paper.book().write("cache",new Gson().toJson(movieResponse));

                                            // store also the category to set the spinner at app start

                                            Paper.book().write("source","movie");

                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<MovieResponse> call,@NonNull Throwable t)
                                    {
                                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                Call<PersonResponse> personResponseCall = retrofitService.getPersonsByQuery(BuildConfig.THE_MOVIE_DB_API_KEY,finalQuery);

                                personResponseCall.enqueue(new Callback<PersonResponse>()
                                {
                                    @Override
                                    public void onResponse(@NonNull Call<PersonResponse> call, @NonNull Response<PersonResponse> response)
                                    {
                                        PersonResponse personResponse = response.body();

                                        if (personResponse != null)
                                        {
                                            List<PersonResponseResults> personResponseResults = personResponse.getResults();

                                            personSearchAdapter = new PersonSearchAdapter(MainActivity.this,personResponseResults);

                                            resultsRecyclerView.setAdapter(personSearchAdapter);

                                            // create some animation to recycler view item loading

                                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this,R.anim.layout_slide_right);

                                            resultsRecyclerView.setLayoutAnimation(controller);
                                            resultsRecyclerView.scheduleLayoutAnimation();

                                            // now store the results in paper database to access offline

                                            Paper.book().write("cache",new Gson().toJson(personResponse));

                                            // store also the category to set the spinner at app start

                                            Paper.book().write("source","person");

                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<PersonResponse> call,@NonNull  Throwable t)
                                    {
                                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }
                }


            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        //set the position of spinner in offline to retrieve at start

        Paper.book().write("position",sourceSpinner.getSelectedIndex());
    }
}



















