package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PagePayments extends Response {
    private int page;
    private int limit;
    private int total;

    @JsonProperty("pagesCount")
    private int pagesCount;

    @JsonProperty("data")
    private PaymentStatusResponse[] data;
}