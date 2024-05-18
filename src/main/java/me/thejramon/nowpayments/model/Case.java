package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Case {

    @JsonProperty("success")
    SUCCESS("success"),

    @JsonProperty("failed")
    FAILED("failed"),

    @JsonProperty("partially_paid")
    PARTIALLY_PAID("partially_paid"),

    @JsonProperty("common")
    COMMON("common");

    private final String value;

    Case(String value) {
        this.value = value;
    }
}