package com.example.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.GenericHandler;

import java.sql.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@Slf4j
@Configuration
public class NotificationProcessor {

    // for testing
    final Set<CustomerReport> reports = new ConcurrentSkipListSet<>();

    @Bean
    IntegrationFlow incoming(Sink sink) {
        return IntegrationFlows.from(sink.input())
                .handle((GenericHandler<CustomerReport>) (customerReport, map) -> {
                    log.info("new report: " + customerReport.toString());
                    this.reports.add(customerReport);
                    return null;
                })
                .get();
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CustomerReport {
    private Long customerId;
    private Date when;
}
