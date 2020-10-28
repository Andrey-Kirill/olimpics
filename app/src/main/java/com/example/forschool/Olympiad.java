package com.example.forschool;

import android.media.Image;

public class Olympiad {

    private String shortName;
    private String fullName;
    private String organizer;
    private String description;
    private String url;
    private String date;
    private String subject;
    private String level;
    

    private int poster;

    public Olympiad(){

    }

    public Olympiad(String shortName, String fullName, String organizer, int poster) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.organizer = organizer;
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganization(String organizer) {
        this.organizer = organizer;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
