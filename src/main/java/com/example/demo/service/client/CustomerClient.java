package com.example.demo.service.client;

import com.example.demo.dto.Customer;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface CustomerClient {

    @GetExchange("/customers")
    Flux<Customer> all();
}
