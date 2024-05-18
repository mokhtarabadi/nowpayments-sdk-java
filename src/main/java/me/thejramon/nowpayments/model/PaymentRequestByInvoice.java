package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentRequestByInvoice {

    @NonNull
    private Long iid;

    @JsonProperty("pay_currency")
    private String payCurrency;

    @JsonProperty("purchase_id")
    private long purchaseId;

    @JsonProperty("order_description")
    private String orderDescription;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("payout_address")
    private String payoutAddress;

    @JsonProperty("payout_extra_id")
    private String payoutExtraId;

    @JsonProperty("payout_currency")
    private String payoutCurrency;
}