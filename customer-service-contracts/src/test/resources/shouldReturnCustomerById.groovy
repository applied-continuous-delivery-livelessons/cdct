package contracts.customers

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description "should return all the customers"
    request {
        url value(consumer(regex("/customers/[0-9]{5}")))
        method GET()
        headers {
            header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
    }
    response {
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
        status 200
        body([])
    }
}