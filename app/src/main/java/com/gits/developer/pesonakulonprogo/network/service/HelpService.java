package com.gits.developer.pesonakulonprogo.network.service;

import com.gits.developer.pesonakulonprogo.model.help.HelpResponServer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kazt on 16/06/17.
 */

public interface HelpService {
    @GET("/api/eholiday/getbantuan")
    Call<HelpResponServer> getHelp();
}
