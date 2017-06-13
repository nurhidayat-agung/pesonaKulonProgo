package com.gits.developer.pesonakulonprogo.network.service;

import com.gits.developer.pesonakulonprogo.model.home.HomeResponData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kazt on 03/06/17.
 */

public interface HomeService {

    @GET("/api/eholiday/getlocation")
    Call<HomeResponData> getLocation();
}
