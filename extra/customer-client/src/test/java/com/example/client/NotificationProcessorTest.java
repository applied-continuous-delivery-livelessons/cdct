package com.example.client;

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
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = "com.example:customer-service-restdocs-messaging", workOffline = true)
@SpringBootTest(classes = CustomerClientApplication.class)
public class NotificationProcessorTest {

    @Autowired
    private NotificationProcessor processor;

    @Autowired
    private StubTrigger stubTrigger;

    @Before
    public void setUp() throws Throwable {
        this.processor.reports.clear();
    }

    @Test
    public void notificationProcessorShouldReceiveANewCustomerReport() throws Throwable {
        this.stubTrigger.trigger("customer_report_message");
        BDDAssertions.then(this.processor.reports.size()).isEqualTo(1);
    }
}


