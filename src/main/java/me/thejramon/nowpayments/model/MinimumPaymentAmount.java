package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MinimumPaymentAmount extends Response {
    @SerializedName("currency_from")
    private String currencyFrom;

    @SerializedName("currency_to")
    private String currencyTo;

    @SerializedName("min_amount")
    private double minAmount;

    @SerializedName("fiat_equivalent")
    private double fiatEquivalent;
}
