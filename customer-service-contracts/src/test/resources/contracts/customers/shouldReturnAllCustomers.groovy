package contracts.customers

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description "should return all the customers"
    request {
        url "/customers"
        method GET()
    }
    response {
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
        status 200
        body([
                [id:1, first:"first1", last: "last1", email: "email1@email.com"],
                [id:2, first:"first2", last: "last2", email: "email2@email.com"]
        ])
    }
}