package com.example.retrofitappdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {
    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String,String> map);

    @GET("/devicename")
    Call<List<DeviceData>> executeDevname();

    @POST("/search")
    Call<List<DeviceData>> executeSearch(@Body HashMap<String,String> name);

    @POST("/compare")
    Call<List<DeviceData>> executeCompare(@Body HashMap<String,String> name);

    @GET("/news")
    Call<List<NewsData>> executeNews();
}
