package ru.sberbank.githubuserinfo;


import java.io.Serializable;

public class User implements Serializable {

    String mLogin;
    String mName;
    String mCompany;
    String mLocation;
    String mAvatarUrl;

    public String getLogin() {
        return mLogin;
    }

    public String getName() {
        return mName;
    }

    public String getCompany() {
        return mCompany;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setCompany(String company) {
        this.mCompany = company;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }
}
