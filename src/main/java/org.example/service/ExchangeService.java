package org.example.service;

import org.example.model.ExchangeRate;
import org.example.repository.ExchangeRateRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

@Service
public class ExchangeService {

    @Value("${external.api.key}")
    private String apiKey;

    private final ExchangeRateRepository repository;

    public ExchangeService(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public double convert(String from, String to, double amount) {
        String pair = from + "_" + to;
        ExchangeRate rate = repository.findById(pair).orElseGet(() -> fetchAndSaveRate(from, to));
        return rate.getRate() * amount;
    }

    public ExchangeRate fetchAndSaveRate(String from, String to) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;
            InputStream is = new URL(url).openStream();
            String jsonText = new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            JSONObject json = new JSONObject(jsonText);
            double rate = json.getJSONObject("conversion_rates").getDouble(to);

            ExchangeRate ex = new ExchangeRate();
            ex.setCurrencyPair(from + "_" + to);
            ex.setRate(rate);
            ex.setLastUpdated(LocalDateTime.now());
            repository.save(ex);
            return ex;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching exchange rate", e);
        }
    }
}
