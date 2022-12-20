package me.thejramon.nowpayments.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Payout{

	private long id;

	@SerializedName("createdAt")
	private Date createdAt;

	private double amount;

	@SerializedName("extra_id")
	private String extraId;

	private String address;

	@SerializedName("requested_at")
	private Date requestedAt;

	private String currency;

	private String error;

	private String hash;

	@SerializedName("batchWithdrawalId")
	private String batchWithdrawalId;

	private Status status;

	@SerializedName("updatedAt")
	private Date updatedAt;
}