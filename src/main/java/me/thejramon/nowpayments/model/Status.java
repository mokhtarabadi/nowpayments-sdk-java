package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    /**
     * waiting - waiting for the customer to send the payment. The initial status of each payment.
     * confirming - the transaction is being processed on the blockchain. Appears when NOWPayments detect the funds from the user on the blockchain.
     * confirmed - the process is confirmed by the blockchain. Customer’s funds have accumulated enough confirmations.
     * sending - the funds are being sent to your personal wallet. We are in the process of sending the funds to you.
     * partially_paid - it shows that the customer sent the less than the actual price. Appears when the funds have arrived in your wallet.
     * finished - the funds have reached your personal address and the payment is finished.
     * failed - the payment wasn't completed due to the error of some kind.
     * refunded - the funds were refunded back to the user.
     * expired - the user didn't send the funds to the specified address in the 24 hour time window.
     */

    @JsonProperty("waiting")
    WAITING("waiting"),

    @JsonProperty("confirming")
    CONFIRMING("confirming"),

    @JsonProperty("confirmed")
    CONFIRMED("confirmed"),

    @JsonProperty("sending")
    SENDING("sending"),

    @JsonProperty("partially_paid")
    PARTIALLY_PAID("partially_paid"),

    @JsonProperty("finished")
    FINISHED("finished"),

    @JsonProperty("failed")
    FAILED("failed"),

    @JsonProperty("refunded")
    REFUNDED("refunded"),

    @JsonProperty("expired")
    EXPIRE("expired");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}