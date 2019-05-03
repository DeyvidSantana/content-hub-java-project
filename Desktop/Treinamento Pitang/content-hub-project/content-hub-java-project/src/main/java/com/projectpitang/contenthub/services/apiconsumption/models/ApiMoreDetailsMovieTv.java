package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.List;

public class ApiMoreDetailsMovieTv {

    // Used in movie
    private int runtime;
    private List<ApiDetailMovieTv> production_countries;

    // Used in tv
    private int number_of_seasons;
    private List<Integer> episode_run_time;

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<ApiDetailMovieTv> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ApiDetailMovieTv> production_countries) {
        this.production_countries = production_countries;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }
}
