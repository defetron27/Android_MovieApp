package com.max.def.movieapp.Adpaters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.def.movieapp.Model.MovieCreditsCrew;
import com.max.def.movieapp.PersonDetailActivity;
import com.max.def.movieapp.R;
import com.max.def.movieapp.ViewHolders.MovieCreditsViewHolder;

import java.util.List;

public class MovieCreditsCrewAdapter extends RecyclerView.Adapter<MovieCreditsViewHolder>
{
    private Activity activity;
    private List<MovieCreditsCrew> movieCreditsCrewList;

    public MovieCreditsCrewAdapter(Activity activity, List<MovieCreditsCrew> movieCreditsCrewList)
    {
        this.activity = activity;
        this.movieCreditsCrewList = movieCreditsCrewList;
    }

    @NonNull
    @Override
    public MovieCreditsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(activity).inflate(R.layout.movie_credits_layout,viewGroup,false);
        return new MovieCreditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCreditsViewHolder movieCreditsViewHolder, int i)
    {
        final MovieCreditsCrew movieCreditsCrew = movieCreditsCrewList.get(i);

        movieCreditsViewHolder.setMovieCreditsImageView(activity,movieCreditsCrew.getProfile_path());

        movieCreditsViewHolder.movieCreditsName.setText(movieCreditsCrew.getName());
        movieCreditsViewHolder.movieCreditsCharacter.setText("Department : " + movieCreditsCrew.getDepartment());

        movieCreditsViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(activity, PersonDetailActivity.class);
                intent.putExtra("id",String.valueOf(movieCreditsCrew.getId()));
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return movieCreditsCrewList.size();
    }
}
