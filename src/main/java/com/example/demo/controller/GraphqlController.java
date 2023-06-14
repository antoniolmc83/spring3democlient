package com.example.demo.controller;

import com.example.demo.dto.Customer;
import com.example.demo.dto.Profile;
import com.example.demo.service.client.CustomerClient;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphqlController {

    private final CustomerClient cc;


    public GraphqlController(CustomerClient cc) {
        this.cc = cc;
    }

    @QueryMapping
    Flux<Customer> customers() {
        return this.cc.all();
    }


    @BatchMapping
    Map<Customer, Profile> profile(List<Customer> customerList) {
        System.out.println("BatchMapping " +customerList);

        var m = new HashMap<Customer, Profile>();
        for( var c: customerList) {
            m.put(c, new Profile(c.id()));
        }
        return m;
    }



/*
    @SchemaMapping( typeName = "Customer")
    Profile profile(Customer c) {
        System.out.println("SchemaMapping " + c.id());
        return new Profile(c.id());
    }
*/

}
