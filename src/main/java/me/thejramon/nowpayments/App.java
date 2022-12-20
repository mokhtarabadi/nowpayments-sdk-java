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

        String testPayload = "{\"payment_id\":5707074779,\"invoice_id\":null,\"payment_status\":\"finished\",\"pay_address\":\"82z6VncRYThPngU4HVtXbejgqSNkoLqQXeN2fXGzWAEjC4SwkZgVKNa4KdDFUkKZVm6J1g77vJ34sSyrgcR3etKPSZi4TBg\",\"price_amount\":2.99,\"price_currency\":\"usd\",\"pay_amount\":0.0247257,\"actually_paid\":0,\"actually_paid_at_fiat\":0,\"pay_currency\":\"xmr\",\"order_id\":\"p_3232\",\"order_description\":\"my own best product\",\"purchase_id\":\"4641811434\",\"created_at\":\"2022-12-20T14:57:11.163Z\",\"updated_at\":\"2022-12-20T14:57:12.580Z\",\"outcome_amount\":2.971742,\"outcome_currency\":\"usdttrc20\"}";
        String testSignature = "fff3bf2a6654907df86c9a66df5f49b6b1a401eeda880182dba91e3589e89145eec0a6c3f88c19541b283817d3b219ddd76d37a1e7956f8755e47071e9bd0569";

        if (!client.checkIPNSignature(testPayload, testSignature)) {
            return;
        }

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
                    PaymentRequest paymentRequest = new PaymentRequest(2.99, "usd", selectedCurrency, "http://78.141.213.248:4690/payment/notify/nowpayments");
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
