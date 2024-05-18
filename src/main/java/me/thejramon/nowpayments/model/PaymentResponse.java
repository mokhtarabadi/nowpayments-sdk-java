package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class PaymentResponse {

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("purchase_id")
    private long purchaseId;

    @JsonProperty("payment_id")
    private long paymentId;

    @JsonProperty("payment_status")
    private Status status;

    @JsonProperty("payin_extra_id")
    private String payInExtraId;

    @JsonProperty("pay_address")
    private String payAddress;
    // required
    @NonNull
    @JsonProperty("price_amount")
    private Double priceAmount;
    // required
    @NonNull
    @JsonProperty("price_currency")
    private String priceCurrency;
    // optional
    @JsonProperty("pay_amount")
    private double payAmount;
    // required
    @NonNull
    @JsonProperty("pay_currency")
    private String payCurrency;
    // required
    @NonNull
    @JsonProperty("ipn_callback_url")
    private String ipnCallbackUrl;
    // optional
    @JsonProperty("order_id")
    private String orderId;
    // optional
    @JsonProperty("order_description")
    private String orderDescription;

}