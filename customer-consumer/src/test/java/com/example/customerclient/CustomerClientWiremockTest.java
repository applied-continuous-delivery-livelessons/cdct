package com.example.customerclient;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = CustomerClientApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = "com.example:customer-producer:+:stubs:8080", workOffline = true)
public class CustomerClientWiremockTest {

    @Autowired
    private CustomerClient client;

    @Test
    public void customersShouldReturnAllCustomers() {

        Collection<Customer> customers = client.getCustomers();
        BDDAssertions.then(customers.size()).isEqualTo(2);
    }

    @Test
    public void customerByIdShouldReturnACustomer() {

        Customer customer = client.getCustomerById(1L);
        BDDAssertions.then(customer.getFirstName()).isEqualToIgnoringCase("first");
        BDDAssertions.then(customer.getLastName()).isEqualToIgnoringCase("last");
        BDDAssertions.then(customer.getEmail()).isEqualToIgnoringCase("email");
        BDDAssertions.then(customer.getId()).isEqualTo(1L);
    }
}
