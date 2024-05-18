package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Estimate extends Response {

    @JsonProperty("currency_from")
    private String currencyFrom;

    @JsonProperty("amount_from")
    private double amountFrom;

    @JsonProperty("currency_to")
    private String currencyTo;

    @JsonProperty("estimated_amount")
    private double estimatedAmount;

}