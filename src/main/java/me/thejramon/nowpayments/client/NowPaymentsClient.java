package me.thejramon.nowpayments.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import me.thejramon.nowpayments.model.*;
import okhttp3.*;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class NowPaymentsClient {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final String HEADER_X_API_KEY = "x-api-key";
    public static final String HEADER_IPN_SIGNATURE = "x-nowpayments-sig";
    private final String baseUrl;
    private final String ipnSecretKey;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public NowPaymentsClient(String apiKey, String ipnSecretKey, boolean sandbox, boolean debug) {
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
        this.objectMapper = new ObjectMapper();

        if (sandbox) {
            this.baseUrl = "https://api-sandbox.nowpayments.io/v1";
        } else {
            this.baseUrl = "https://api.nowpayments.io/v1";
        }
        this.ipnSecretKey = ipnSecretKey;
    }

    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) throws IOException {
        String url = baseUrl + "/invoice";
        String json = this.objectMapper.writeValueAsString(invoiceRequest);
        String response = this.post(url, json);
        return this.objectMapper.readValue(response, InvoiceResponse.class);
    }

    public AuthResponse auth(String email, String password) throws IOException {
        String url = baseUrl + "/auth";
        Auth auth = new Auth(email, password);
        String json = this.objectMapper.writeValueAsString(auth);
        String response = this.post(url, json);
        return this.objectMapper.readValue(response, AuthResponse.class);
    }

    public Estimate getEstimatedPrice(double amount, String currencyFrom, String currencyTo) throws IOException {
        String url = baseUrl + "/estimate";
        Map<String, String> params = new HashMap<>();
        params.put("amount", amount + "");
        params.put("currency_from", currencyFrom);
        params.put("currency_to", currencyTo);
        String response = this.get(url, params);
        return this.objectMapper.readValue(response, Estimate.class);
    }

    public MinimumPaymentAmount getMinimumPaymentAmount(String currencyFrom, @Nullable String currencyTo) throws IOException {
        String url = baseUrl + "/min-amount";
        Map<String, String> params = new HashMap<>();
        params.put("currency_from", currencyFrom);
        if (Objects.nonNull(currencyTo)) {
            params.put("currency_to", currencyTo);
        }
        String response = this.get(url, params);
        return this.objectMapper.readValue(response, MinimumPaymentAmount.class);
    }

    public FullCurrencies getFullCurrencies() throws IOException {
        String url = baseUrl + "/full-currencies";
        String response = this.get(url, Collections.emptyMap());
        return this.objectMapper.readValue(response, FullCurrencies.class);
    }

    public Currencies getCurrencies() throws IOException {
        String url = baseUrl + "/currencies";
        String response = this.get(url, Collections.emptyMap());
        return this.objectMapper.readValue(response, Currencies.class);
    }

    public PaymentStatusResponse getPaymentStatus(long paymentId) throws IOException {
        String url = baseUrl + "/payment/" + paymentId;
        String response = this.get(url, Collections.emptyMap());
        return this.objectMapper.readValue(response, PaymentStatusResponse.class);
    }

    public PaymentResponse createPayment(PaymentRequest paymentRequest) throws IOException {
        String url = baseUrl + "/payment";
        String json = this.objectMapper.writeValueAsString(paymentRequest);
        String response = this.post(url, json);
        return this.objectMapper.readValue(response, PaymentResponse.class);
    }

    public PaymentStatusResponse createPaymentByInvoice(PaymentResponseByInvoice paymentResponseByInvoice) throws IOException {
        String url = baseUrl + "/invoice-payment";
        String json = this.objectMapper.writeValueAsString(paymentResponseByInvoice);
        String response = this.post(url, json);
        return this.objectMapper.readValue(response, PaymentStatusResponse.class);
    }

    public Currencies getAvailableCheckedCurrencies() throws IOException {
        String url = baseUrl + "/merchant/coins";
        String response = this.get(url, Collections.emptyMap());
        return this.objectMapper.readValue(response, Currencies.class);
    }

    public PagePayments getPagePayments() throws IOException {
        throw new RuntimeException("This method is not completed");
    }

    public me.thejramon.nowpayments.model.Response status() throws IOException {
        String url = baseUrl + "/status";
        String response = this.get(url, Collections.emptyMap());
        return this.objectMapper.readValue(response, me.thejramon.nowpayments.model.Response.class);
    }

    public IPNResponse checkIPNSignature(String payload, String signatureHeader) throws IOException {
        // Parse the payload into a JsonNode
        JsonNode jsonNode = objectMapper.readTree(payload);

        // Create a new ObjectNode to hold the sorted payload
        ObjectNode sortedNode = objectMapper.createObjectNode();

        // Sort the keys and add them to the sortedNode
        jsonNode.fieldNames().forEachRemaining(fieldName -> {
            sortedNode.set(fieldName, jsonNode.get(fieldName));
        });

        String sortedPayload = objectMapper.writeValueAsString(sortedNode);
        log.debug("sorted payload: {}", sortedPayload);

        String signature = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, ipnSecretKey.getBytes(StandardCharsets.UTF_8)).hmacHex(sortedPayload);
        log.debug("calculated signature: {}", signature);

        // Check the signature
        if (signature.equals(signatureHeader)) {
            log.trace("two signatures match");
            PaymentStatusResponse paymentStatusResponse = objectMapper.readValue(sortedPayload, PaymentStatusResponse.class);
            IPNResponse ipnResponse = new IPNResponse(true);
            ipnResponse.setPaymentStatusResponse(paymentStatusResponse);
            return ipnResponse;
        } else {
            log.trace("signature mismatch: {}", signatureHeader);
            return new IPNResponse(false);
        }
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    private String get(String url, Map<String, String> params) throws IOException {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        params.forEach(urlBuilder::addQueryParameter);
        String finalUrl = urlBuilder.build().toString();
        Request request = new Request.Builder().url(finalUrl).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }
}