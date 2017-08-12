package com.example.customers;

import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@AutoConfigureStubRunner(ids = "com.example:customer-service-restdocs-messaging:+:8003", workOffline = true)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerClientNotificationProcessorTest {

    @Autowired
    private NotificationProcessor processor;

    @Autowired
    private Sink sink;

    @Autowired
    private StubTrigger stubTrigger;

    @Before
    public void setUp() throws Throwable {
        this.processor.reports.clear();
    }


    @Test
    public void should_receive_new_fraud_message_integration() throws Throwable {
        String name = "Long";
        this.stubTrigger.trigger("customer_report_message");
        BDDAssertions.then(this.processor.reports.size()).isEqualTo(1);
    }
}


