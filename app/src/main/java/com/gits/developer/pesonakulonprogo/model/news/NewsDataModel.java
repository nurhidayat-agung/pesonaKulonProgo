package com.gits.developer.pesonakulonprogo.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kazt on 05/06/17.
 */

public class NewsDataModel implements Serializable{
    @SerializedName("id_news")
    @Expose
    private String id_news;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("image")
    @Expose
    private List<NewsImageList> image = null;

    public String getId_news() {
        return id_news;
    }

    public void setId_news(String id_news) {
        this.id_news = id_news;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<NewsImageList> getImage() {
        return image;
    }

    public void setImage(List<NewsImageList> image) {
        this.image = image;
    }
}
