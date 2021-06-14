package com.example.interview;

import com.example.interview.operators.Operators;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApicallInterface {

    @FormUrlEncoded
    @POST("/api/app/retailer/recharge/prepaidrecharge/getoperator")
    Call<Operators>fetchOperators(@FieldMap Map<String,String> requestBody);

    @FormUrlEncoded
    @POST("/api/app/retailer/recharge/prepaidrecharge/process")
    Call<JsonObject> submitTransaction(@FieldMap Map<String,String> requestBody);

}
