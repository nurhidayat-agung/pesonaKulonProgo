package com.gits.developer.pesonakulonprogo.model.help;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazt on 16/06/17.
 */

public class HelpResponServer implements Serializable {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<HelpModel> data = null;

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

    public List<HelpModel> getData() {
        return data;
    }

    public void setData(List<HelpModel> data) {
        this.data = data;
    }
}
