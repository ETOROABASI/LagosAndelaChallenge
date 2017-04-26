package com.example.android.lagosandelachallenge.retroServices;

import com.example.android.lagosandelachallenge.model.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ETORO on 26/04/2017.
 */

public interface ApiService {


    @GET("/search/users")
    Call<UserList> getUserList(@Query("q") String filter);

}
