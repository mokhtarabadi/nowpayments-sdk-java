package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {

    private long id;

    private String code;

    private String name;

    private boolean enable;

    @JsonProperty("wallet_regex")
    private String walletRegex;

    private int priority;

    @JsonProperty("extra_id_exists")
    private boolean extraIdExists;

    @JsonProperty("extra_id_regex")
    private String extraIdRegex;

    @JsonProperty("logo_url")
    private String logoUrl;

    private boolean track;

    @JsonProperty("cg_id")
    private String cgId;

    @JsonProperty("is_maxlimit")
    private boolean isMaxLimit;

    private String network;

    @JsonProperty("smart_contract")
    private String smartContract;

    @JsonProperty("network_precision")
    private String networkPrecision;

}