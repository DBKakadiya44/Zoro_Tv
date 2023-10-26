package com.zorotv.DataModel;

import com.google.gson.annotations.Expose;

public class MovieData {

    @Expose
    private String name;
    @Expose
    private String thumbnail;
    @Expose
    private String banner;
    @Expose
    private String about;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
