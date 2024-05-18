package me.thejramon.nowpayments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Payout {

	private long id;

	private double amount;

	@JsonProperty("extra_id")
	private String extraId;

	private String address;

	@JsonProperty("requested_at")
	private Date requestedAt;

	private String currency;

	private String error;

	private String hash;

	@JsonProperty("batchWithdrawalId")
	private String batchWithdrawalId;

	private Status status;

	@JsonProperty("createdAt")
	private Date createdAt;

	@JsonProperty("updatedAt")
	private Date updatedAt;
}