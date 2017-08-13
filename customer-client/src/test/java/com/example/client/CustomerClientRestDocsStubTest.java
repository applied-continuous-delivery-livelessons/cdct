package com.example.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.contains;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = "com.example:customer-service-restdocs:+:8002", workOffline = true)
@SpringBootTest(classes = CustomerClientApplication.class, properties = {"customer-service.host=http://localhost:8002"})
public class CustomerClientRestDocsStubTest {

    @Autowired
    private CustomerClient client;

    @Test
    public void getCustomers() throws Exception {
        Collection<com.example.client.Customer> customers = this.client.getCustomers();
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
