package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

@Parcel
public class Price {
    @JsonProperty
    String currency;
    @JsonProperty
    double amount;

    public Price() {
        //required by jackson
    }

    public Price(String currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }
}
