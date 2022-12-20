package me.thejramon.nowpayments.model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
public class Currencies extends Response {

    private List<String> currencies = new ArrayList<>();

}
