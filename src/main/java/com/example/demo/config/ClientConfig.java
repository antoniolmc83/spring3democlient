package com.example.demo.config;

import com.example.demo.service.client.CustomerClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Bean
    public ApplicationListener<ApplicationReadyEvent> ready(CustomerClient cc) {
        return event -> cc.all().subscribe(System.out::println);
    }

    @Bean
    public CustomerClient cc(WebClient.Builder httpBuilder) {
        var cc = httpBuilder.baseUrl("http://localhost:8080/")
                .build();

        return HttpServiceProxyFactory
                .builder()
                .clientAdapter(WebClientAdapter.forClient(cc))
                .build()
                .createClient( CustomerClient.class );
    }

}
