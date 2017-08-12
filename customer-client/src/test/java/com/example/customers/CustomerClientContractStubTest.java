package com.example.customers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.contains;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
//@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerClientApplication.class, properties = {"customer-service.host=http://localhost:8003"})
@AutoConfigureStubRunner(ids = "com.example:customer-service-contracts:+:8003", workOffline = true)
public class CustomerClientContractStubTest {

    @Autowired
    private CustomerClient client;

    @Test
    public void getCustomers() throws Exception {
        Collection<Customer> customers = this.client.getCustomers();
        Assert.assertThat(customers, contains(
                new Customer(1L, "first1", "last1", "email1@email.com"),
                new Customer(2L, "first2", "last2", "email2@email.com")));
    }

    @Test
    public void getCustomerById() {
        Customer customerById = this.client.getCustomerById(1L);
        Assert.assertThat(customerById, org.hamcrest.Matchers.notNullValue());
    }
}
