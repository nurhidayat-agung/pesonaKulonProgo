package com.gits.developer.pesonakulonprogo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.gits.developer.pesonakulonprogo.BR;

import java.io.Serializable;

/**
 * Created by kazt on 31/05/17.
 */

public class LoginFBData extends BaseObservable implements Serializable {
    private String username;
    private String password;
    private String name;
    private String gender;
    private String imgAvatar;
    private int remember;
    private int sosmed;

    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public int getRemember() {
        return remember;
    }

    public void setRemember(int remember) {
        this.remember = remember;
    }

    public int getSosmed() {
        return sosmed;
    }

    public void setSosmed(int sosmed) {
        this.sosmed = sosmed;
    }
}
