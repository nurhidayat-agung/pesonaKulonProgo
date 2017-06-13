package com.gits.developer.pesonakulonprogo.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazt on 03/06/17.
 */

public class HomeResponData implements Serializable {
    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<HomeDataModel> date;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomeDataModel> getDate() {
        return date;
    }

    public void setDate(List<HomeDataModel> date) {
        this.date = date;
    }
}
