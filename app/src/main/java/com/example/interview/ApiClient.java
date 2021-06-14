package com.example.interview;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit=null;
   static HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();


    static Retrofit getRetrofitCLient(){
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY
        );
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        retrofit=new Retrofit.Builder().baseUrl("https://uat.rnfi.in").addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
