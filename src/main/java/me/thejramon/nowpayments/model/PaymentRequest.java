package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentRequest {

    @NonNull
    @JsonProperty("price_amount")
    private Double priceAmount;

    @NonNull
    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("pay_amount")
    private double payAmount;

    @NonNull
    @JsonProperty("pay_currency")
    private String payCurrency;

    @NonNull
    @JsonProperty("ipn_callback_url")
    private String ipnCallbackUrl;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_description")
    private String orderDescription;

    @JsonProperty("is_fixed_rate")
    private boolean isFixedRate;

    @JsonProperty("is_fee_paid_by_user")
    private boolean isFeePaidByUser;
}