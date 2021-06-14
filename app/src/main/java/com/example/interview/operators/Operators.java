
package com.example.interview.operators;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Operators {

    @SerializedName("rechageonetimetoken")
    @Expose
    private String rechageonetimetoken;
    @SerializedName("all_operator")
    @Expose
    private List<AllOperator> allOperator = null;
    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getRechageonetimetoken() {
        return rechageonetimetoken;
    }

    public void setRechageonetimetoken(String rechageonetimetoken) {
        this.rechageonetimetoken = rechageonetimetoken;
    }

    public List<AllOperator> getAllOperator() {
        return allOperator;
    }

    public void setAllOperator(List<AllOperator> allOperator) {
        this.allOperator = allOperator;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
