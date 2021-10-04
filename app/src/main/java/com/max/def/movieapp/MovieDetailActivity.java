package com.max.def.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.max.def.movieapp.Adpaters.MovieCreditsCastAdapter;
import com.max.def.movieapp.Adpaters.MovieCreditsCrewAdapter;
import com.max.def.movieapp.Adpaters.MoviePosterImagesAdapter;
import com.max.def.movieapp.Adpaters.MovieProductionCompaniesAdapter;
import com.max.def.movieapp.Adpaters.MovieVideosAdapter;
import com.max.def.movieapp.Client.RetrofitClient;
import com.max.def.movieapp.Interfaces.RetrofitService;
import com.max.def.movieapp.Model.MovieCredits;
import com.max.def.movieapp.Model.MovieCreditsCast;
import com.max.def.movieapp.Model.MovieCreditsCrew;
import com.max.def.movieapp.Model.MovieDetails;
import com.max.def.movieapp.Model.MovieDetailsGenres;
import com.max.def.movieapp.Model.MovieDetailsProductionCompanies;
import com.max.def.movieapp.Model.MovieDetailsProductionCountries;
import com.max.def.movieapp.Model.MovieImages;
import com.max.def.movieapp.Model.MovieImagesBackDropsAndPosters;
import com.max.def.movieapp.Model.MovieVideos;
import com.max.def.movieapp.Model.MovieVideosResults;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity
{
    private KenBurnsView movieDetailPosterImageView;

    private CircleImageView movieDetailBackDropPosterCircleImageView;

    private ArcProgress movieDetailRatingBar;

    private LinearLayoutCompat movieDetailOriginalTitleLayout;
    private LinearLayoutCompat movieDetailOriginalLanguageLayout;
    private LinearLayoutCompat movieDetailAdultLayout;
    private LinearLayoutCompat movieDetailStatusLayout;
    private LinearLayoutCompat movieDetailRuntimeLayout;
    private LinearLayoutCompat movieDetailBudgetLayout;
    private LinearLayoutCompat movieDetailRevenueLayout;
    private LinearLayoutCompat movieDetailGenreLayout;
    private LinearLayoutCompat movieDetailProductionCountryLayout;
    private LinearLayoutCompat movieDetailReleaseDateLayout;
    private LinearLayoutCompat movieDetailHomepageLayout;
    private LinearLayoutCompat movieDetailOverviewLayout;
    private LinearLayoutCompat movieDetailCastLayout;
    private LinearLayoutCompat movieDetailCrewLayout;
    private LinearLayoutCompat movieDetailProductionCompanyLayout;
    private LinearLayoutCompat movieDetailImagesLayout;
    private LinearLayoutCompat movieDetailVideosLayout;

    private AppCompatTextView movieDetailTitle;
    private AppCompatTextView movieDetailOriginalTitle;
    private AppCompatTextView movieDetailOriginalLanguage;
    private AppCompatTextView movieDetailAdult;
    private AppCompatTextView movieDetailStatus;
    private AppCompatTextView movieDetailRuntime;
    private AppCompatTextView movieDetailBudget;
    private AppCompatTextView movieDetailRevenue;
    private AppCompatTextView movieDetailGenre;
    private AppCompatTextView movieDetailProductionCountry;
    private AppCompatTextView movieDetailReleaseDate;
    private AppCompatTextView movieDetailHomepage;
    private AppCompatTextView movieDetailOverview;

    private RecyclerView movieDetailCastRecyclerView;
    private RecyclerView movieDetailCrewRecyclerView;
    private RecyclerView movieDetailProductionCompanyRecyclerView;
    private RecyclerView movieDetailImagesRecyclerView;
    private RecyclerView movieDetailVideosRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();

        RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);

        ThumbnailLoader.initialize(BuildConfig.GOOGLE_CLOUD_API_KEY);

        movieDetailPosterImageView = findViewById(R.id.movie_detail_poster_image_view);

        movieDetailBackDropPosterCircleImageView = findViewById(R.id.movie_detail_poster_circle_image_view);

        movieDetailRatingBar = findViewById(R.id.movie_detail_rating_bar);

        movieDetailOriginalTitleLayout = findViewById(R.id.movie_detail_original_title_layout);
        movieDetailOriginalLanguageLayout = findViewById(R.id.movie_detail_original_language_layout);
        movieDetailAdultLayout = findViewById(R.id.movie_detail_adult_layout);
        movieDetailStatusLayout = findViewById(R.id.movie_detail_status_layout);
        movieDetailRuntimeLayout = findViewById(R.id.movie_detail_runtime_layout);
        movieDetailBudgetLayout = findViewById(R.id.movie_detail_budget_layout);
        movieDetailRevenueLayout = findViewById(R.id.movie_detail_revenue_layout);
        movieDetailGenreLayout = findViewById(R.id.movie_detail_genre_layout);
        movieDetailProductionCountryLayout = findViewById(R.id.movie_detail_production_country_layout);
        movieDetailReleaseDateLayout = findViewById(R.id.movie_detail_release_date_layout);
        movieDetailHomepageLayout = findViewById(R.id.movie_detail_homepage_layout);
        movieDetailOverviewLayout = findViewById(R.id.movie_detail_overview_layout);
        movieDetailCastLayout = findViewById(R.id.movie_detail_cast_layout);
        movieDetailCrewLayout = findViewById(R.id.movie_detail_crew_layout);
        movieDetailProductionCompanyLayout = findViewById(R.id.movie_detail_production_company_layout);
        movieDetailImagesLayout = findViewById(R.id.movie_detail_images_layout);
        movieDetailVideosLayout = findViewById(R.id.movie_detail_videos_layout);

        movieDetailTitle = findViewById(R.id.movie_detail_title);
        movieDetailOriginalTitle = findViewById(R.id.movie_detail_original_title);
        movieDetailOriginalLanguage = findViewById(R.id.movie_detail_original_language);
        movieDetailAdult = findViewById(R.id.movie_detail_adult);
        movieDetailStatus = findViewById(R.id.movie_detail_status);
        movieDetailRuntime = findViewById(R.id.movie_detail_runtime);
        movieDetailBudget = findViewById(R.id.movie_detail_budget);
        movieDetailRevenue = findViewById(R.id.movie_detail_revenue);
        movieDetailGenre = findViewById(R.id.movie_detail_genre);
        movieDetailProductionCountry = findViewById(R.id.movie_detail_production_country);
        movieDetailReleaseDate = findViewById(R.id.movie_detail_release_date);
        movieDetailHomepage = findViewById(R.id.movie_detail_homepage);
        movieDetailOverview = findViewById(R.id.movie_detail_overview);

        movieDetailCastRecyclerView = findViewById(R.id.movie_detail_cast_recycler_view);
        movieDetailCastRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        movieDetailCrewRecyclerView = findViewById(R.id.movie_detail_crew_recycler_view);
        movieDetailCrewRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        movieDetailProductionCompanyRecyclerView = findViewById(R.id.movie_detail_production_company_recycler_view);
        movieDetailProductionCompanyRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        movieDetailImagesRecyclerView = findViewById(R.id.movie_detail_images_recycler_view);
        movieDetailImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        movieDetailVideosRecyclerView = findViewById(R.id.movie_detail_videos_recycler_view);
        movieDetailVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        if (intent != null && intent.getExtras() != null)
        {
            if (intent.getExtras().getString("id") != null)
            {
                int id = Integer.parseInt(intent.getExtras().getString("id"));

                Call<MovieDetails> movieDetailsCall = retrofitService.getMovieDetailsById(id,BuildConfig.THE_MOVIE_DB_API_KEY);

                movieDetailsCall.enqueue(new Callback<MovieDetails>()
                {
                    @Override
                    public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response)
                    {
                        MovieDetails movieDetailsResponse = response.body();

                        if (movieDetailsResponse != null)
                        {
                            prepareMovieDetails(movieDetailsResponse);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t)
                    {

                    }
                });

                Call<MovieCredits> movieCreditsCall = retrofitService.getMovieCreditsById(id,BuildConfig.THE_MOVIE_DB_API_KEY);

                movieCreditsCall.enqueue(new Callback<MovieCredits>()
                {
                    @Override
                    public void onResponse(@NonNull Call<MovieCredits> call, @NonNull Response<MovieCredits> response)
                    {
                        MovieCredits movieCreditsResponse = response.body();

                        if (movieCreditsResponse != null)
                        {
                            List<MovieCreditsCast> movieCreditsCastList = movieCreditsResponse.getCast();

                            if (movieCreditsCastList != null && movieCreditsCastList.size() > 0)
                            {
                                MovieCreditsCastAdapter movieCreditsCastAdapter = new MovieCreditsCastAdapter(MovieDetailActivity.this,movieCreditsCastList);

                                movieDetailCastRecyclerView.setAdapter(movieCreditsCastAdapter);

                                movieDetailCastLayout.setVisibility(View.VISIBLE);

                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_right);

                                movieDetailCastRecyclerView.setLayoutAnimation(controller);
                                movieDetailCastRecyclerView.scheduleLayoutAnimation();
                            }
                            else
                            {
                                movieDetailCastLayout.setVisibility(View.GONE);
                            }

                            List<MovieCreditsCrew> movieCreditsCrewsList = movieCreditsResponse.getCrew();

                            if (movieCreditsCrewsList != null && movieCreditsCrewsList.size() > 0)
                            {
                                MovieCreditsCrewAdapter movieCreditsCrewAdapter = new MovieCreditsCrewAdapter(MovieDetailActivity.this,movieCreditsCrewsList);

                                movieDetailCrewRecyclerView.setAdapter(movieCreditsCrewAdapter);

                                movieDetailCrewLayout.setVisibility(View.VISIBLE);

                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_right);

                                movieDetailCrewRecyclerView.setLayoutAnimation(controller);
                                movieDetailCrewRecyclerView.scheduleLayoutAnimation();
                            }
                            else
                            {
                                movieDetailCrewLayout.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            movieDetailCastLayout.setVisibility(View.GONE);
                            movieDetailCrewLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieCredits> call, @NonNull Throwable t)
                    {

                    }
                });

                Call<MovieImages> movieImagesCall = retrofitService.getMovieImagesById(id,BuildConfig.THE_MOVIE_DB_API_KEY);

                movieImagesCall.enqueue(new Callback<MovieImages>()
                {
                    @Override
                    public void onResponse(@NonNull Call<MovieImages> call, @NonNull Response<MovieImages> response)
                    {
                        MovieImages movieImages = response.body();

                        if (movieImages != null)
                        {
                            ArrayList<MovieImagesBackDropsAndPosters> movieImagesBackDropsAndPostersArrayList = new ArrayList<>();

                            List<MovieImagesBackDropsAndPosters> movieImagesBackDropsList = movieImages.getBackdrops();
                            List<MovieImagesBackDropsAndPosters> movieImagesPosterList = movieImages.getBackdrops();

                            if (movieImagesBackDropsList != null && movieImagesBackDropsList.size() > 0)
                            {
                                if (movieImagesPosterList != null && movieImagesPosterList.size() > 0)
                                {
                                    movieImagesBackDropsAndPostersArrayList.addAll(movieImagesBackDropsList);
                                    movieImagesBackDropsAndPostersArrayList.addAll(movieImagesPosterList);
                                }
                                else
                                {
                                    movieImagesBackDropsAndPostersArrayList.addAll(movieImagesBackDropsList);
                                }
                            }
                            else if (movieImagesPosterList != null && movieImagesPosterList.size() > 0)
                            {
                                movieImagesBackDropsAndPostersArrayList.addAll(movieImagesPosterList);
                            }
                            else
                            {
                                movieImagesBackDropsAndPostersArrayList.clear();

                                movieDetailImagesLayout.setVisibility(View.GONE);
                            }

                            if (movieImagesBackDropsAndPostersArrayList.size() > 0)
                            {
                                MoviePosterImagesAdapter moviePosterImagesAdapter = new MoviePosterImagesAdapter(MovieDetailActivity.this,movieImagesBackDropsAndPostersArrayList);

                                movieDetailImagesRecyclerView.setAdapter(moviePosterImagesAdapter);

                                movieDetailImagesLayout.setVisibility(View.VISIBLE);

                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_right);

                                movieDetailImagesRecyclerView.setLayoutAnimation(controller);
                                movieDetailImagesRecyclerView.scheduleLayoutAnimation();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieImages> call,@NonNull Throwable t)
                    {

                    }
                });

                Call<MovieVideos> movieVideosCall = retrofitService.getMovieVideosById(id,BuildConfig.THE_MOVIE_DB_API_KEY);

                movieVideosCall.enqueue(new Callback<MovieVideos>()
                {
                    @Override
                    public void onResponse(@NonNull Call<MovieVideos> call, @NonNull Response<MovieVideos> response)
                    {
                        MovieVideos movieVideos = response.body();

                        if (movieVideos != null)
                        {
                            List<MovieVideosResults> movieVideosResultsList = movieVideos.getResults();

                            if (movieVideosResultsList != null && movieVideosResultsList.size() > 0)
                            {
                                movieDetailVideosLayout.setVisibility(View.VISIBLE);

                                MovieVideosAdapter adapter = new MovieVideosAdapter(MovieDetailActivity.this,movieVideosResultsList);

                                movieDetailVideosRecyclerView.setAdapter(adapter);

                                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_right);

                                movieDetailVideosRecyclerView.setLayoutAnimation(controller);
                                movieDetailVideosRecyclerView.scheduleLayoutAnimation();
                            }
                            else
                            {
                                movieDetailVideosLayout.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            movieDetailVideosLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieVideos> call,@NonNull Throwable t)
                    {

                    }
                });
            }
        }
    }

    private void prepareMovieDetails(MovieDetails movieDetailsResponse)
    {
        String posterPath = movieDetailsResponse.getPoster_path();
        String backDropPath = movieDetailsResponse.getBackdrop_path();

        Double voteAverage = movieDetailsResponse.getVote_average() * 10;

        String title = movieDetailsResponse.getTitle();
        String originalTitle = movieDetailsResponse.getOriginal_title();
        String originalLanguage = movieDetailsResponse.getOriginal_language();

        boolean adult = movieDetailsResponse.isAdult();

        String status = movieDetailsResponse.getStatus();

        Integer runtime = movieDetailsResponse.getRuntime();
        Integer budget = movieDetailsResponse.getBudget();
        Integer revenue = movieDetailsResponse.getRevenue();

        List<MovieDetailsGenres> movieDetailsGenresList = movieDetailsResponse.getGenres();
        List<MovieDetailsProductionCountries> movieDetailsProductionCountriesList = movieDetailsResponse.getProduction_countries();

        String releaseDate = movieDetailsResponse.getRelease_date();
        String homepage = movieDetailsResponse.getHomepage();
        String overview = movieDetailsResponse.getOverview();

        List<MovieDetailsProductionCompanies> movieDetailsProductionCompaniesList = movieDetailsResponse.getProduction_companies();

        Picasso.with(this).load(posterPath).into(movieDetailPosterImageView);
        Picasso.with(this).load(backDropPath).into(movieDetailBackDropPosterCircleImageView);

        int rating = voteAverage.intValue();
        movieDetailRatingBar.setProgress(rating);

        movieDetailTitle.setText(title);

        if (originalTitle != null)
        {
            if (originalTitle.length() > 0)
            {
                movieDetailOriginalTitle.setText(originalTitle);
                movieDetailOriginalTitleLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailOriginalTitleLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailOriginalTitleLayout.setVisibility(View.GONE);
        }

        if (originalLanguage != null)
        {
            if (originalLanguage.length() > 0)
            {
                movieDetailOriginalLanguage.setText(originalLanguage);
                movieDetailOriginalLanguageLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailOriginalLanguageLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailOriginalLanguageLayout.setVisibility(View.GONE);
        }

        if (adult)
        {
            movieDetailAdult.setText("Yes");
            movieDetailAdultLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            movieDetailAdult.setText("No");
            movieDetailAdultLayout.setVisibility(View.VISIBLE);
        }

        if (status != null)
        {
            if (status.length() > 0)
            {
                movieDetailStatus.setText(status);
                movieDetailStatusLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailStatusLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailStatusLayout.setVisibility(View.GONE);
        }

        if (runtime != null)
        {
            if (runtime != 0)
            {
                movieDetailRuntime.setText(String.valueOf(runtime));
                movieDetailRuntimeLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailRuntimeLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailRuntimeLayout.setVisibility(View.GONE);
        }

        if (budget != null)
        {
            if (budget != 0)
            {
                movieDetailBudget.setText(String.valueOf(budget));
                movieDetailBudgetLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailBudgetLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailBudgetLayout.setVisibility(View.GONE);
        }

        if (revenue != null)
        {
            if (revenue != 0)
            {
                movieDetailRevenue.setText(String.valueOf(revenue));
                movieDetailRevenueLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailRevenueLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailRevenueLayout.setVisibility(View.GONE);
        }

        if (movieDetailsGenresList != null && movieDetailsGenresList.size() > 0)
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i=0; i<movieDetailsGenresList.size(); i++)
            {
                if (i == movieDetailsGenresList.size() - 1)
                {
                    stringBuilder.append(movieDetailsGenresList.get(i).getName());
                }
                else
                {
                    stringBuilder.append(movieDetailsGenresList.get(i).getName()).append(", ");
                }
            }

            movieDetailGenre.setText(stringBuilder.toString());
            movieDetailGenreLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            movieDetailGenreLayout.setVisibility(View.GONE);
        }

        if (movieDetailsProductionCountriesList != null && movieDetailsProductionCountriesList.size() > 0)
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i=0; i<movieDetailsProductionCountriesList.size(); i++)
            {
                if (i == movieDetailsProductionCountriesList.size() - 1)
                {
                    stringBuilder.append(movieDetailsProductionCountriesList.get(i).getName());
                }
                else
                {
                    stringBuilder.append(movieDetailsProductionCountriesList.get(i).getName()).append(", ");
                }
            }

            movieDetailProductionCountry.setText(stringBuilder.toString());
            movieDetailProductionCountryLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            movieDetailProductionCountryLayout.setVisibility(View.GONE);
        }

        if (releaseDate != null)
        {
            if (releaseDate.length() > 0)
            {
                movieDetailReleaseDate.setText(releaseDate);
                movieDetailReleaseDateLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailReleaseDateLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailReleaseDateLayout.setVisibility(View.GONE);
        }

        if (homepage != null)
        {
            if (homepage.length() > 0)
            {
                movieDetailHomepage.setText(homepage);
                movieDetailHomepageLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailHomepageLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailHomepageLayout.setVisibility(View.GONE);
        }

        if (overview != null)
        {
            if (overview.length() > 0)
            {
                movieDetailOverview.setText(overview);
                movieDetailOverviewLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                movieDetailOverviewLayout.setVisibility(View.GONE);
            }
        }
        else
        {
            movieDetailOverviewLayout.setVisibility(View.GONE);
        }

        if (movieDetailsProductionCompaniesList != null && movieDetailsProductionCompaniesList.size() > 0)
        {
            MovieProductionCompaniesAdapter movieProductionCompaniesAdapter = new MovieProductionCompaniesAdapter(MovieDetailActivity.this,movieDetailsProductionCompaniesList);

            movieDetailProductionCompanyRecyclerView.setAdapter(movieProductionCompaniesAdapter);

            movieDetailProductionCompanyLayout.setVisibility(View.VISIBLE);

            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MovieDetailActivity.this,R.anim.layout_slide_right);

            movieDetailProductionCompanyRecyclerView.setLayoutAnimation(controller);
            movieDetailProductionCompanyRecyclerView.scheduleLayoutAnimation();
        }
        else
        {
            movieDetailProductionCompanyLayout.setVisibility(View.GONE);
        }
    }

    // set animation for back to main activity

    @Override
    public void finish()
    {
        super.finish();

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
