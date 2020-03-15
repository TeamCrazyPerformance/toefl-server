package com.tcp.toeflserver.security;

public class JwtProperties {
    public static final String SECRET = "tcp";
    public static final int EXPIRATION_TIME = 24*60*60*1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
