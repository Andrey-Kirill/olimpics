package com.example.forschool;

import java.util.ArrayList;

public class UserProfile {

    ArrayList<Olympiad> favoriteOlympiads = new ArrayList<>();

    String name;
    String surname;
    String id;
    String password;
    String email;

    private static UserProfile userProfile;

    private UserProfile(String name, String surname, String password, String email, String id) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    private UserProfile(String id) {
        this.id = id;
    }

    public void addOlympiadToFavorite(Olympiad olympiad) {
        favoriteOlympiads.add(olympiad);
    }

    public void removeOlympiadFromFavorite(Olympiad olympiad) {
        favoriteOlympiads.remove(olympiad);
    }

    public void updateOlympiad(Olympiad olympiad) {
        if (favoriteOlympiads.contains(olympiad)) {
            favoriteOlympiads.remove(olympiad);
        } else {
            favoriteOlympiads.add(olympiad);
        }
    }

    public ArrayList<Olympiad> getFavoriteOlympiads() {
        return favoriteOlympiads;
    }

    public static UserProfile getUserProfile() {
        return userProfile;
    }

    public static void setUserProfile(String name, String surname, String password, String email, String id) {
        userProfile = new UserProfile(name, surname, password, email, id);
    }

    public static void setUserProfile(String id) {
        userProfile = new UserProfile(id);
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
