package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class InvoiceRequest {
    @NonNull
    @JsonProperty("price_amount")
    private Double priceAmount;

    @NonNull
    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("pay_currency")
    private String payCurrency;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_description")
    private String orderDescription;

    @JsonProperty("ipn_callback_url")
    private String ipnCallbackUrl;

    @JsonProperty("success_url")
    private String successUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;

    @JsonProperty("partially_paid_url")
    private String partiallyPaidUrl;

    @JsonProperty("is_fixed_rate")
    private boolean isFixedRate;

    @JsonProperty("is_fee_paid_by_user")
    private boolean isFeePaidByUser;
}