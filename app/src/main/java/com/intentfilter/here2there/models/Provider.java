package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Provider {
    @JsonProperty
    String providerIconUrl;
    @JsonProperty
    String disclaimer;
    @JsonProperty
    String iosTunesUrl;
    @JsonProperty
    String iosAppUrl;
    @JsonProperty
    String androidPackageName;
    @JsonProperty
    String displayName;
}
