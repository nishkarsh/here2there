package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {
    @JsonProperty
    private String currency;
    @JsonProperty
    private double amount;
}
