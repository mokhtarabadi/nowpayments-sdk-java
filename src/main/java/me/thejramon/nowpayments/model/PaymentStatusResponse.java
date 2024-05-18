package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
public class PaymentStatusResponse extends Response {

    @JsonProperty("payment_id")
    private long paymentId;

    @JsonProperty("payment_status")
    private Status paymentStatus;

    @JsonProperty("pay_address")
    private String payAddress;

    @JsonProperty("payin_extra_id")
    private String payInExtraId;

    private String network;

    @JsonProperty("amount_received")
    private double amountReceived;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("price_amount")
    private double priceAmount;

    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("pay_amount")
    private double payAmount;

    @JsonProperty("actually_paid")
    private double actuallyPaid;

    @JsonProperty("pay_currency")
    private String payCurrency;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("purchase_id")
    private long purchaseId;

    @JsonProperty("outcome_currency")
    private String outcomeCurrency;

    @JsonProperty("outcome_amount")
    private double outcomeAmount;

    // optional
    @JsonProperty("order_id")
    private String orderId;
    // optional
    @JsonProperty("order_description")
    private String orderDescription;
}