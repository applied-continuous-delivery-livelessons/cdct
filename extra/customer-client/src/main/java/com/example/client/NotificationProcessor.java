package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@Slf4j
@Component
public class NotificationProcessor {

    // visible for testing
    Set<CustomerReport> reports = new ConcurrentSkipListSet<>();

    @StreamListener(Sink.INPUT)
    public void incomingReport(CustomerReport customerReport) {
        log.info("new report: " + customerReport.toString());
        this.reports.add(customerReport);
    }
}

