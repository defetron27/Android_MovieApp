package com.max.def.movieapp.Adpaters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.def.movieapp.ImageViewerActivity;
import com.max.def.movieapp.Model.PersonImagesProfiles;
import com.max.def.movieapp.R;
import com.max.def.movieapp.ViewHolders.ImagesViewHolder;

import java.util.List;

public class PersonProfileImagesAdapter extends RecyclerView.Adapter<ImagesViewHolder>
{
    private Activity activity;
    private List<PersonImagesProfiles> profileImagesList;

    public PersonProfileImagesAdapter(Activity activity, List<PersonImagesProfiles> profileImagesList)
    {
        this.activity = activity;
        this.profileImagesList = profileImagesList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(activity).inflate(R.layout.profile_images_layout,viewGroup,false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImagesViewHolder imagesViewHolder, int i)
    {
        final PersonImagesProfiles imagesProfiles = profileImagesList.get(i);

        imagesViewHolder.setProfileImage(activity,imagesProfiles.getFile_path());

        imagesViewHolder.profileImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent imageViewerIntent = new Intent(activity, ImageViewerActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imagesViewHolder.profileImage, ViewCompat.getTransitionName(imagesViewHolder.profileImage));
                imageViewerIntent.putExtra("image_url",imagesProfiles.getFile_path());
                activity.startActivity(imageViewerIntent,compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileImagesList.size();
    }
}
