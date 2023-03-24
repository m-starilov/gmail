package com.gmail.model;

import java.util.Objects;

public class Profile {
    private String login;
    private String password;

    public Profile(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(login, profile.login) && Objects.equals(password, profile.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
