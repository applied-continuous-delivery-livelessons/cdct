package contracts.customers

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should produce a customer report message"
    label "customer_report_message"
    input {
        triggeredBy("triggerMessage()")
    }
    outputMessage {
        sentTo "customers"
        body([customerId: 1L])
    }
}