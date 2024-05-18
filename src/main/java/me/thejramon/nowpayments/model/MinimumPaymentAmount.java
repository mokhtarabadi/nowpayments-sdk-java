package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MinimumPaymentAmount extends Response {
    @JsonProperty("currency_from")
    private String currencyFrom;

    @JsonProperty("currency_to")
    private String currencyTo;

    @JsonProperty("min_amount")
    private double minAmount;

    @JsonProperty("fiat_equivalent")
    private double fiatEquivalent;
}