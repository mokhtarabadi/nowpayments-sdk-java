package me.thejramon.nowpayments.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthResponse extends Response {
    @NonNull
    private String token;
}
