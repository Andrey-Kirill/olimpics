package com.example.forschool;

import java.util.ArrayList;

public class UserProfile {

    ArrayList<Olympiad> favoriteOlympiads;

    String name;
    String surname;
    String id;
    String password;
    String email;

    String generateUsedID() {
        return "sample ID";
    }

    public UserProfile(String name, String surname, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.id = generateUsedID();
    }

    public void addOlympiadToFavorite(Olympiad olympiad) {
        favoriteOlympiads.add(olympiad);
    }

    public ArrayList<Olympiad> getFavoriteOlympiads() {
        return favoriteOlympiads;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setFavoriteOlympiads(ArrayList<Olympiad> favoriteOlympiads) {
        this.favoriteOlympiads = favoriteOlympiads;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
