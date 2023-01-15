package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class PaymentResponse {

    @SerializedName("created_at")
    private Long createdAt;

    @SerializedName("updated_at")
    private Long updatedAt;

    @SerializedName("purchase_id")
    private long purchaseId;

    @SerializedName("payment_id")
    private long paymentId;

    @SerializedName("payment_status")
    private Status status;

    @SerializedName("payin_extra_id")
    private String payInExtraId;

    @SerializedName("pay_address")
    private String payAddress;
    // required
    @NonNull
    @SerializedName("price_amount")
    private Double priceAmount;
    // required
    @NonNull
    @SerializedName("price_currency")
    private String priceCurrency;
    // optional
    @SerializedName("pay_amount")
    private double payAmount;
    // required
    @NonNull
    @SerializedName("pay_currency")
    private String payCurrency;
    // required
    @NonNull
    @SerializedName("ipn_callback_url")
    private String ipnCallbackUrl;
    // optional
    @SerializedName("order_id")
    private String orderId;
    // optional
    @SerializedName("order_description")
    private String orderDescription;

}
