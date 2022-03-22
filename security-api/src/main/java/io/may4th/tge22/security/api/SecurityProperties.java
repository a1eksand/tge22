package io.may4th.tge22.security.api;

public interface SecurityProperties {

    String getTokenSecret();

    Long getTokenLifetime();
}
