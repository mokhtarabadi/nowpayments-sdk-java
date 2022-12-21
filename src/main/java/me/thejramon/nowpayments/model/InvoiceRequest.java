package me.thejramon.nowpayments.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class InvoiceRequest {

    /**
     * price_amount (required) - the amount that users have to pay for the order stated in fiat currency. In case you do not indicate the price in crypto, our system will automatically convert this fiat amount into its crypto equivalent. NOTE: Some of the assets (KISHU, NWC, FTT, CHR, XYM, SRK, KLV, SUPER, OM, XCUR, NOW, SHIB, SAND, MATIC, CTSI, MANA, FRONT, FTM, DAO, LGCY), have a maximum price limit of ~$2000.
     * price_currency (required) - the fiat currency in which the price_amount is specified (usd, eur, etc).
     * pay_currency (optional) - the specified crypto currency (btc, eth, etc). If not specified, can be chosen on the invoice_url
     * ipn_callback_url (optional) - url to receive callbacks, should contain "http" or "https", eg. "https://nowpayments.io"
     * order_id (optional) - internal store order ID, e.g. "RGDBP-21314"
     * order_description (optional) - internal store order description, e.g. "Apple Macbook Pro 2019 x 1"
     * success_url(optional) - url where the customer will be redirected after successful payment.
     * cancel_url(optional) - url where the customer will be redirected after failed payment.
     */

    // required
    @NonNull
    @SerializedName(value = "price_amount")
    private Double priceAmount;
    // required
    @NonNull
    @SerializedName(value = "price_currency")
    private String priceCurrency;

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

}
