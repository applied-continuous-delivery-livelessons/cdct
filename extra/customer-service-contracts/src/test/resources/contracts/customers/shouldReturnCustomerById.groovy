package contracts.customers

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description "should return a specific customer"
    request {
        url $(regex("/customers/1"))
        method GET()
    }
    response {
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
        status 200
        body([email: "email@email.com", first: "first", last: "last"])
    }
}