package me.thejramon.nowpayments.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Estimate extends Response {

    @SerializedName("currency_from")
    private String currencyFrom;
    @SerializedName("amount_from")
    private double amountFrom;
    @SerializedName("currency_to")
    private String currencyTo;
    @SerializedName("estimated_amount")
    private double estimatedAmount;

}
