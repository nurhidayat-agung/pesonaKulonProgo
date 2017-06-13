package com.gits.developer.pesonakulonprogo.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazt on 05/06/17.
 */

public class NewsResponServer implements Serializable {
    @SerializedName("status")
    @Expose
    private boolean status;


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<NewsDataModel> data = null;

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

    public List<NewsDataModel> getData() {
        return data;
    }

    public void setData(List<NewsDataModel> data) {
        this.data = data;
    }
}
