package me.thejramon.nowpayments;


import lombok.extern.slf4j.Slf4j;
import me.thejramon.nowpayments.client.NowPaymentsClient;
import me.thejramon.nowpayments.model.PaymentRequest;
import me.thejramon.nowpayments.model.PaymentStatusResponse;

import java.io.IOException;
import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {
        NowPaymentsClient client = new NowPaymentsClient("W10TJCG-2WEMYSF-Q38DKPA-R4A6K93", "X7ig+P9WNwPQ/PexR0u8k7Ute9PANTBn", true, false);

        try {
            // 1. first check status of NowPayments API
            if (client.status().getMessage().equals("OK")) {
                // 2. If required, check the list of available payment currencies
                List<String> availableCurrencies = client.getCurrencies().getCurrencies();
                // 3. ask user to choose currency
                String selectedCurrency = availableCurrencies.get(0);
                // 4. Get the minimum payment amount for the selected currency pair
                double minimumAmount = client.getMinimumPaymentAmount(selectedCurrency, null).getMinAmount();
                // 5. Get the estimate of the total amount in crypto and check that it is larger than the minimum payment amount from step 4
                double estimatedAmount = client.getEstimatedPrice(2.99, "usd", selectedCurrency).getEstimatedAmount();
                if (estimatedAmount > minimumAmount) {
                    // 6. create a payment and get the deposit address
                    PaymentRequest paymentRequest = new PaymentRequest(2.99, "usd", selectedCurrency, "https://example.com/");
                    paymentRequest.setOrderId("p_3232");
                    paymentRequest.setOrderDescription("my own best product");
                    paymentRequest.setPayAmount(estimatedAmount);
                    paymentRequest.setFixedRate(true);
                    paymentRequest.setFeePaidByUser(true);

                    PaymentStatusResponse paymentStatusResponse = client.createPayment(paymentRequest);

                    // 6. Ask a customer to send the payment to the generated deposit address
                    long paymentId = paymentStatusResponse.getPaymentId();
                    String depositAddress = paymentStatusResponse.getPayAddress();
                    String memoOrTag = paymentStatusResponse.getPayInExtraId();
                    String network = paymentStatusResponse.getNetwork();

                    // 7. Get notify from ipnCallbackUrl or user click I send the payment and then get payment status
                    PaymentStatusResponse refreshedStatusResponse = client.getPaymentStatus(paymentId);

                    log.info("final result: {}", refreshedStatusResponse.toString());
                } else {
                    log.error("estimated amount is lower than minimum amount");
                }
            } else {
                log.warn("status of NowPayments API is not OK");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
