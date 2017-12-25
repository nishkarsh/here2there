package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {
    @JsonProperty
    private String currency;
    @JsonProperty
    private double amount;

    public Price() {
        //required by jackson
    }

    public Price(String currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }
}
