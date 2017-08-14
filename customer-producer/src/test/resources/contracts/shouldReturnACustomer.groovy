import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "/customers/{id} should return a single JSON customer"

    request {
        url "/customers/1"
        method GET()
    }
    response {
        status 200
        headers {
            contentType(applicationJsonUtf8())
        }
        body([id: 1, firstName: "first", lastName: "last", email: "email"])
    }
}