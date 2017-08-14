package com.example.customers;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author <a href="josh@joshlong.com">Josh Long</a>
 */
@RestController
public class CustomerRestController {

    private final CustomerRepository customerRepository;
    private final Source source;

    public CustomerRestController(CustomerRepository customerRepository,
                                  Source source) {
        this.customerRepository = customerRepository;
        this.source = source;
    }

    @GetMapping("/customers")
    Collection<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findOne(id);
    }

    @PostMapping("/customers/report/{customerId}")
    void processLongRunningReportForCustomer(@PathVariable Long customerId) {
        this.source.output().send(
                MessageBuilder.withPayload(
                        new CustomerReport(customerId)).build());
    }

}
