package io.aperofy.authentication;

public interface SecurityParams {

    String HEADER_NAME = "Authorization";
    String SECRET = "ramaBNHY";
    long EXPIRATION = 1000 * 24 * 3600 * 1000;
    String HEADER_PREFIX = "Bearer ";
}
