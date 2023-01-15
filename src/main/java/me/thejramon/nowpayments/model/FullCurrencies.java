package me.thejramon.nowpayments.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FullCurrencies extends Response {

    private List<Currency> currencies = new ArrayList<>();

}
