package me.thejramon.nowpayments.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Auth {
    @NonNull
    private String email;

    @NonNull
    private String password;
}
