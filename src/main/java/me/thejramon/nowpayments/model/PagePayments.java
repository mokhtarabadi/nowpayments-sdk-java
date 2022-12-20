package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PagePayments extends Response {
    private int page;

    private int limit;

    private int total;

    @SerializedName("pagesCount")
    private int pagesCount;

    @SerializedName("data")
    private PaymentStatusResponse[] data;
}
