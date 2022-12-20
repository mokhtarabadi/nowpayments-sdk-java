package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentRequest {
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

    @SerializedName("fixed_rate")
    private boolean fixedRate;

    @SerializedName("is_fee_paid_by_user")
    private boolean isFeePaidByUser;
}
