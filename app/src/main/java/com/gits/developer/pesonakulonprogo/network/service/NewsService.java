package com.gits.developer.pesonakulonprogo.network.service;

import com.gits.developer.pesonakulonprogo.model.news.NewsResponServer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kazt on 05/06/17.
 */

public interface NewsService {

    @GET("/api/eholiday/getnews")
    Call<NewsResponServer> getNews();
}
