package me.thejramon.nowpayments.model;

import com.google.gson.annotations.SerializedName;

public enum Case {

    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("failed")
    FAILED("failed"),

    @SerializedName("partially_paid")
    PARTIALLY_PAID("partially_paid"),

    @SerializedName("common")
    COMMON("common");

    private final String value;

    Case(String value) {
        this.value = value;
    }
}

