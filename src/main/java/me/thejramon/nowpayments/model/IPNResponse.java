package me.thejramon.nowpayments.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class IPNResponse {
    @NonNull
    private Boolean verified;

    private PaymentStatusResponse paymentStatusResponse;
}
