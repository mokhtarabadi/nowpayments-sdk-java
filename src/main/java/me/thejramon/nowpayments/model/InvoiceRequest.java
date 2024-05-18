package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class InvoiceRequest {
    @NonNull
    @SerializedName(value = "price_amount")
    private Double priceAmount;

    @NonNull
    @SerializedName(value = "price_currency")
    private String priceCurrency;

    @SerializedName("pay_currency")
    private String payCurrency;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("order_description")
    private String orderDescription;

    @SerializedName("ipn_callback_url")
    private String ipnCallbackUrl;

    @SerializedName("success_url")
    private String successUrl;

    @SerializedName("cancel_url")
    private String cancelUrl;

    @SerializedName("partially_paid_url")
    private String partiallyPaidUrl;


    @SerializedName("is_fixed_rate")
    private boolean isFixedRate;
    @SerializedName("is_fee_paid_by_user")
    private boolean isFeePaidByUser;


}
