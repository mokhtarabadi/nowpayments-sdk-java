package me.thejramon.nowpayments.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentRequestByInvoice {

    // required
    @NonNull
    private Long iid;
    // required
    @SerializedName("pay_currency")
    private String payCurrency;

    @SerializedName("purchase_id")
    private long purchaseId;

    @SerializedName("order_description")
    private String orderDescription;

    @SerializedName("customer_email")
    private String customerEmail;

    @SerializedName("payout_address")
    private String payoutAddress;

    @SerializedName("payout_extra_id")
    private String payoutExtraId;

    @SerializedName("payout_currency")
    private String payoutCurrency;
}
