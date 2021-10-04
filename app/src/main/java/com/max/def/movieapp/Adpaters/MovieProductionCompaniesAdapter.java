package com.max.def.movieapp.Adpaters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.def.movieapp.Model.MovieCreditsCast;
import com.max.def.movieapp.Model.MovieDetailsProductionCompanies;
import com.max.def.movieapp.PersonDetailActivity;
import com.max.def.movieapp.R;
import com.max.def.movieapp.ViewHolders.MovieCreditsViewHolder;
import com.max.def.movieapp.ViewHolders.MovieProductionCompaniesViewHolder;

import java.util.List;

public class MovieProductionCompaniesAdapter extends RecyclerView.Adapter<MovieProductionCompaniesViewHolder>
{
    private Activity activity;
    private List<MovieDetailsProductionCompanies> movieDetailsProductionCompaniesList;

    public MovieProductionCompaniesAdapter(Activity activity, List<MovieDetailsProductionCompanies> movieDetailsProductionCompaniesList)
    {
        this.activity = activity;
        this.movieDetailsProductionCompaniesList = movieDetailsProductionCompaniesList;
    }

    @NonNull
    @Override
    public MovieProductionCompaniesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(activity).inflate(R.layout.production_company_layout,viewGroup,false);
        return new MovieProductionCompaniesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieProductionCompaniesViewHolder movieProductionCompaniesViewHolder, int i)
    {
        final MovieDetailsProductionCompanies movieDetailsProductionCompanies = movieDetailsProductionCompaniesList.get(i);

        movieProductionCompaniesViewHolder.setProductionCompanyImageView(activity,movieDetailsProductionCompanies.getLogo_path());

        movieProductionCompaniesViewHolder.productionCompanyName.setText(movieDetailsProductionCompanies.getName());
    }

    @Override
    public int getItemCount()
    {
        return movieDetailsProductionCompaniesList.size();
    }
}
