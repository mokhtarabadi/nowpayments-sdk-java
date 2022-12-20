package me.thejramon.nowpayments;


import lombok.extern.slf4j.Slf4j;
import me.thejramon.nowpayments.client.NowPaymentsClient;
import me.thejramon.nowpayments.model.PaymentResponseByInvoice;
import me.thejramon.nowpayments.model.PaymentStatusResponse;

import java.io.IOException;

@Slf4j
public class App {

    public static void main(String[] args) {
        NowPaymentsClient client = new NowPaymentsClient("W10TJCG-2WEMYSF-Q38DKPA-R4A6K93", true, false);

        try {
            PaymentStatusResponse response = client.createPaymentByInvoice(new PaymentResponseByInvoice(0L, "USD"));
            log.debug(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
