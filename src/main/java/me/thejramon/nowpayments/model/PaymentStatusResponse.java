package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
public class PaymentStatusResponse extends Response {

    @SerializedName("payment_id")
    private long paymentId;

    @SerializedName("payment_status")
    private Status paymentStatus;

    @SerializedName("pay_address")
    private String payAddress;

    @SerializedName("price_amount")
    private double priceAmount;

    @SerializedName("price_currency")
    private String priceCurrency;

    @SerializedName("pay_amount")
    private double payAmount;

    @SerializedName("actually_paid")
    private double actuallyPaid;

    @SerializedName("pay_currency")
    private String payCurrency;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("purchase_id")
    private long purchaseId;

    @SerializedName("outcome_currency")
    private String outcomeCurrency;

    @SerializedName("outcome_amount")
    private double outcomeAmount;
}
