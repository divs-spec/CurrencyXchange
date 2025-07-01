package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    private String currencyPair;

    private double rate;

    private LocalDateTime lastUpdated;

    public ExchangeRate() {}

    public ExchangeRate(String currencyPair, double rate, LocalDateTime lastUpdated) {
        this.currencyPair = currencyPair;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
