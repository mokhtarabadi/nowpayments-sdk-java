package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentRequest {

    @NonNull
    @SerializedName("price_amount")
    private Double priceAmount;

    @NonNull
    @SerializedName("price_currency")
    private String priceCurrency;

    @SerializedName("pay_amount")
    private double payAmount;

    @NonNull
    @SerializedName("pay_currency")
    private String payCurrency;
    @NonNull
    @SerializedName("ipn_callback_url")
    private String ipnCallbackUrl;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("order_description")
    private String orderDescription;

    @SerializedName("is_fixed_rate")
    private boolean isFixedRate;

    @SerializedName("is_fee_paid_by_user")
    private boolean isFeePaidByUser;
}
