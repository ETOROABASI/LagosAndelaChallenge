package com.example.android.lagosandelachallenge.retroServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ETORO on 26/04/2017.
 */

public class ApiBuilder {



    public static final String BASE_URL = "https://api.github.com";

    private Retrofit retrofit;

    public ApiBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }
}

