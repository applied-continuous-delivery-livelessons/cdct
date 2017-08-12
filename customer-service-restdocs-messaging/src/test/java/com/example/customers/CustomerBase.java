package com.example.customers;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@Slf4j
@SpringBootTest(classes = CustomerServiceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMessageVerifier
public class CustomerBase {

    @Autowired
    private CustomerRestController customerRestController;

    @Before
    public void before() {
        RestAssuredMockMvc.standaloneSetup(this.customerRestController);
    }

    public void triggerMessage() throws Exception {
        this.customerRestController
                .processLongRunningReportForCustomer(1L);

    }
}

