package com.example.customers;

import com.example.customerclient.Customer;
import com.example.customerclient.CustomerClient;
import com.example.customerclient.CustomerClientApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.contains;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerClientApplication.class, properties = {"customer-service.host=http://localhost:8002"})
@AutoConfigureWireMock(stubs = "classpath:/META-INF/com.example/customer-service-restdocs/**/*.json", port = 8002)
public class CustomerClientRestDocsStubTest {

    @Autowired
    private CustomerClient client;

    @Test
    public void getCustomers() throws Exception {
        Collection<Customer> customers = this.client.getCustomers();
        Assert.assertThat(customers, contains(
                new Customer(1L, "first", "last", "email@email.com"),
                new Customer(2L, "first", "last", "email@email.com")));
    }

    @Test
    public void getCustomerById() {
        Customer customerById = this.client.getCustomerById(1L);
        Assert.assertThat(customerById, org.hamcrest.Matchers.notNullValue());
    }
}
