package com.intentfilter.here2there.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

@Parcel
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

    public String getDisplayName() {
        return displayName;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getAppLink() {
        return iosTunesUrl;
    }
}
