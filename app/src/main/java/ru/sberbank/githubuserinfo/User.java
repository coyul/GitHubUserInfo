package ru.sberbank.githubuserinfo;


import java.io.Serializable;

public class User implements Serializable {

    String mLogin;
    String mName;
    String mCompany;
    String mLocation;
    String mAvatarUrl;

    public User() {
    }

    public User(User user) {
        this.mLogin = user.getLogin();
        this.mName = user.getName();
        this.mCompany = user.getCompany();
        this.mLocation = user.getLocation();
        this.mAvatarUrl = user.getAvatarUrl();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!mLogin.equals(user.mLogin)) return false;
        if (mName != null ? !mName.equals(user.mName) : user.mName != null) return false;
        if (mCompany != null ? !mCompany.equals(user.mCompany) : user.mCompany != null)
            return false;
        if (mLocation != null ? !mLocation.equals(user.mLocation) : user.mLocation != null)
            return false;
        return mAvatarUrl != null ? mAvatarUrl.equals(user.mAvatarUrl) : user.mAvatarUrl == null;

    }

    @Override
    public int hashCode() {
        int result = mLogin.hashCode();
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mCompany != null ? mCompany.hashCode() : 0);
        result = 31 * result + (mLocation != null ? mLocation.hashCode() : 0);
        result = 31 * result + (mAvatarUrl != null ? mAvatarUrl.hashCode() : 0);
        return result;
    }
}
