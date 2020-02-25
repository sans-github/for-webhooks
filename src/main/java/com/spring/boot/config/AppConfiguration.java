package com.spring.boot.config;

import com.braintreegateway.BraintreeGateway;
import org.springframework.context.annotation.Bean;

public class AppConfiguration {

    @Bean
    public BraintreeGateway braintreeGateway() {
        final String merchantId= System.getenv("merchantId");
        final String publicKey = System.getenv("publicKey");
        final String privateKey = System.getenv("privateKey");

        return new BraintreeGateway("sandbox",merchantId, publicKey, privateKey);
    }
}
