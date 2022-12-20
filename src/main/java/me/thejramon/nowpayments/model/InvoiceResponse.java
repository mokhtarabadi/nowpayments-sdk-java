package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceResponse extends Response {

    private long id;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("order_description")
    private String orderDescription;

    @SerializedName("price_amount")
    private double priceAmount;

    @SerializedName("price_currency")
    private String priceCurrency;

    @SerializedName("pay_currency")
    private String payCurrency;

    @SerializedName("ipn_callback_url")
    private String ipnCallbackUrl;

    @SerializedName("invoice_url")
    private String invoiceUrl;

    @SerializedName("success_url")
    private String successUrl;

    @SerializedName("cancel_url")
    private String cancelUrl;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

}
