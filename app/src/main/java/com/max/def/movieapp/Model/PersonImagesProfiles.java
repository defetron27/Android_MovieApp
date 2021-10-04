package com.max.def.movieapp.Model;

public class PersonImagesProfiles
{
    private String iso_639_1;

    private Integer width;

    private Integer height;

    private Integer vote_count;

    private Double vote_average;

    private String file_path;

    private Double aspect_ratio;

    public PersonImagesProfiles() {
    }

    public PersonImagesProfiles(String iso_639_1, Integer width, Integer height, Integer vote_count, Double vote_average, String file_path, Double aspect_ratio) {
        this.iso_639_1 = iso_639_1;
        this.width = width;
        this.height = height;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.file_path = file_path;
        this.aspect_ratio = aspect_ratio;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getFile_path()
    {
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Double getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(Double aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }
}
