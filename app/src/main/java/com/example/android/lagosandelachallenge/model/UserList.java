package com.example.android.lagosandelachallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ETORO on 26/04/2017.
 */

public class UserList {

    @SerializedName("items")
    @Expose
    private List<UserData> items = null;

    public List<UserData> getItems() {
        return items;
    }

    public void setItems(List<UserData> items) {
        this.items = items;
    }
}
