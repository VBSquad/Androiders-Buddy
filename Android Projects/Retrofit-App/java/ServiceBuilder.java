package com.example.retrofit_open_source;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.retrofit_open_source.ApiParam.BASE_URL;

public class ServiceBuilder {
    public static <T> T build(Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(clazz);
    }

}
