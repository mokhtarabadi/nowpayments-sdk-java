package me.thejramon.nowpayments.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Currency {

    private long id;

    private String code;

    private String name;

    private boolean enable;

    @SerializedName("wallet_regex")
    private String walletRegex;

    private int priority;

    @SerializedName("extra_id_exists")
    private boolean extraIdExists;

    @SerializedName("extra_id_regex")
    private String extraIdRegex;

    @SerializedName("logo_url")
    private String logoUrl;

    private boolean track;

    @SerializedName("cg_id")
    private String cgId;

    @SerializedName("is_maxlimit")
    private boolean isMaxLimit;

    private String network;

    @SerializedName("smart_contract")
    private String smartContract;

    @SerializedName("network_precision")
    private String networkPrecision;

}
