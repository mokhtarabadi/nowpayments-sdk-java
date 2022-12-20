package me.thejramon.nowpayments.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import me.thejramon.nowpayments.model.*;
import okhttp3.Response;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class NowPaymentsClient {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final String HEADER_X_API_KEY = "x-api-key";
    private static String BASE_URL = "";
    private final OkHttpClient httpClient;
    private final Gson gson;

    public NowPaymentsClient(String apiKey, boolean sandbox, boolean debug) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(chain -> {
            Request request = chain.request();
            Request newRequest = request.newBuilder()
                    .addHeader(HEADER_X_API_KEY, apiKey)
                    .build();
            return chain.proceed(newRequest);
        });

        if (debug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        this.httpClient = builder.build();
        this.gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().setLenient().create();

        if (sandbox) {
            BASE_URL = "https://api-sandbox.nowpayments.io/v1";
        } else {
            BASE_URL = "https://api.nowpayments.io/v1";
        }
    }

    public AuthResponse auth(String email, String password) throws IOException {
        String url = BASE_URL + "/auth";
        Auth auth = new Auth(email, password);
        String json = this.gson.toJson(auth);
        String response = this.post(url, json);
        return this.gson.fromJson(response, AuthResponse.class);
    }

    public Currencies getCurrencies() throws IOException {
        String url = BASE_URL + "/currencies";
        String response = this.get(url, Collections.<String, String>emptyMap());
        return this.gson.fromJson(response, Currencies.class);
    }

    public FullCurrencies getFullCurrencies() throws IOException {
        String url = BASE_URL + "/full-currencies";
        String response = this.get(url, Collections.<String, String>emptyMap());
        return this.gson.fromJson(response, FullCurrencies.class);
    }

    public Currencies getAvailableCheckedCurrencies() throws IOException {
        String url = BASE_URL + "/merchant/coins";
        String response = this.get(url, Collections.<String, String>emptyMap());
        return this.gson.fromJson(response, Currencies.class);
    }

    public Estimate getEstimatedPrice(double amount, String currencyFrom, String currencyTo) throws IOException {
        String url = BASE_URL + "/estimate";

        Map<String, String> params = new HashMap<String, String>();
        params.put("amount", amount + "");
        params.put("currency_from", currencyFrom);
        params.put("currency_to", currencyTo);
        String response = this.get(url, params);
        return this.gson.fromJson(response, Estimate.class);
    }

    public PaymentStatusResponse createPayment(PaymentRequest paymentRequest) throws IOException {
        String url = BASE_URL + "/payment";
        String json = this.gson.toJson(paymentRequest);
        String response = this.post(url, json);
        return this.gson.fromJson(response, PaymentStatusResponse.class);
    }

    public PaymentStatusResponse createPaymentByInvoice(PaymentResponseByInvoice paymentResponseByInvoice) throws IOException {
        String url = BASE_URL + "/invoice-payment";
        String json = this.gson.toJson(paymentResponseByInvoice);
        String response = this.post(url, json);
        return this.gson.fromJson(response, PaymentStatusResponse.class);
    }

    public PaymentStatusResponse getPaymentStatusResponse(long paymentId) throws IOException {
        String url = BASE_URL + "/payment/" + paymentId;
        String response = this.get(url, Collections.<String, String>emptyMap());
        return this.gson.fromJson(response, PaymentStatusResponse.class);
    }

    public MinimumPaymentAmount getMinimumPaymentAmount(String currencyFrom, String currencyTo) throws IOException {
        String url = BASE_URL + "/min-amount";
        Map<String, String> params = new HashMap<String, String>();
        params.put("currency_from", currencyFrom);
        params.put("currency_to", currencyTo);
        String response = this.get(url, params);
        return this.gson.fromJson(response, MinimumPaymentAmount.class);
    }

    public PagePayments getPagePayments() throws IOException {
        throw new RuntimeException("This method is not completed");
    }

    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) throws IOException {
        String url = BASE_URL + "/invoice";
        String json = this.gson.toJson(invoiceRequest);
        String response = this.post(url, json);
        return this.gson.fromJson(response, InvoiceResponse.class);
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    private String get(String url, Map<String, String> params) throws IOException {
        HttpUrl.Builder urlBuilder
                = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        params.forEach(urlBuilder::addQueryParameter);
        String finalUrl = urlBuilder.build().toString();
        Request request = new Request.Builder().url(finalUrl).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }
}
