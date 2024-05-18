package me.thejramon.nowpayments.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response {
    private boolean status;

    @JsonProperty("statusCode")
    private int statusCode;

    private String code;

    private String message;
}
