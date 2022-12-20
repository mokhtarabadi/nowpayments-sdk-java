package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Response {
    private boolean status;

    @SerializedName("statusCode")
    private int statusCode;

    private String code;

    private String message;
}
