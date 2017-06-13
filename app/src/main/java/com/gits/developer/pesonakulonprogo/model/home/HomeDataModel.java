package com.gits.developer.pesonakulonprogo.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazt on 03/06/17.
 */

public class HomeDataModel implements Serializable{

    @SerializedName("id_location")
    @Expose
    private String id_location;


    @SerializedName("lat")
    @Expose
    private Double lat;

    @SerializedName("long")
    @Expose
    private Double longth;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("image")
    @Expose
    private List<HomeImgData> image;


    public String getId_location() {
        return id_location;
    }

    public void setId_location(String id_location) {
        this.id_location = id_location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongth() {
        return longth;
    }

    public void setLongth(Double longth) {
        this.longth = longth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<HomeImgData> getImage() {
        return image;
    }

    public void setImage(List<HomeImgData> image) {
        this.image = image;
    }
}
