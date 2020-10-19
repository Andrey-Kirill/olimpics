package com.example.forschool;

import android.media.Image;

public class Olympiad {

    private String shortName;
    private String fullName;
    private String organization;
    private String description;
    private String url;
    private String date;
    

    private int level;
    private int poster;

    public Olympiad(String shortName, String fullName, String organization, int poster) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.organization = organization;
        this.poster = poster;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

}
