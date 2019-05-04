package com.projectpitang.contenthub.dto;

import com.projectpitang.contenthub.models.TV;

public class TvDTO extends ProgramDTO {

    private int seansons;

    public int getSeansons() {
        return seansons;
    }

    public void setSeansons(int seansons) {
        this.seansons = seansons;
    }

    public TV transformToTv(){
        TV tv = new TV();
        tv.setTitle(this.getTitle());
        tv.setOverview(this.getOverview());
        tv.setOriginCountry(this.getOriginCountry());
        tv.setLanguage(this.getLanguage());
        tv.setReleaseYear(this.getReleaseYear());
        tv.setRuntime(this.getRuntime());
        tv.setSeansons(this.seansons);

        return tv;
    }

}
