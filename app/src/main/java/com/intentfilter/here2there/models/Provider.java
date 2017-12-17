package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Provider {
    @JsonProperty
    private String providerIconUrl;
    @JsonProperty
    private String disclaimer;
    @JsonProperty
    private String iosTunesUrl;
    @JsonProperty
    private String iosAppUrl;
    @JsonProperty
    private String androidPackageName;
    @JsonProperty
    private String displayName;
}
