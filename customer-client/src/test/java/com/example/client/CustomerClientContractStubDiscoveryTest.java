package com.example.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.hamcrest.Matchers.contains;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("discovery")
@AutoConfigureStubRunner(ids = "com.example:customer-service-restdocs-messaging-discovery", workOffline = true)
@SpringBootTest(classes = {CustomerClientConfiguration.class, DiscoveryAwareCustomerClientConfiguration.class},
        properties = {"customer-service.host=http://customer-service"})
public class CustomerClientContractStubDiscoveryTest {


    @Autowired
    private CustomerClient client;

    @Test
    public void getCustomers() throws Exception {
        Collection<Customer> customers = this.client.getCustomers();
        Assert.assertThat(customers, contains(
                new com.example.client.Customer(1L, "first", "last", "email@email.com"),
                new com.example.client.Customer(2L, "first", "last", "email@email.com")));
    }

    @Test
    public void getCustomerById() {
        Customer customerById = this.client.getCustomerById(1L);
        Assert.assertThat(customerById, org.hamcrest.Matchers.notNullValue());
    }
}

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@Configuration
@EnableDiscoveryClient
@EnableAutoConfiguration
class DiscoveryAwareCustomerClientConfiguration {

    @Bean
    @LoadBalanced
    @Profile("discovery")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
