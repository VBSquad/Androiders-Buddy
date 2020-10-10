package com.example.retrofit_open_source;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDataService {
    @GET("/repositories/19438/issues")
    Call<List<ApiData>> getData();
}
