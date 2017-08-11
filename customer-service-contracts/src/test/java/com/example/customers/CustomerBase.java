package com.example.customers;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = CustomerServiceApplication.class)
@RunWith(SpringRunner.class)
public class CustomerBase {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRestController customerRestController;

    @Before
    public void before() {
        Mockito.when(this.customerRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Customer(1L, "first1", "last1", "email1@email.com"),
                        new Customer(2L, "first2", "last2", "email2@email.com")));
        RestAssuredMockMvc.standaloneSetup(this.customerRestController);
    }
}
