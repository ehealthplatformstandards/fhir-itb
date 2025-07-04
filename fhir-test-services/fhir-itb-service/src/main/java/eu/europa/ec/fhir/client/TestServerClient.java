package eu.europa.ec.fhir.client;

import org.springframework.web.client.RestClient;

public class TestServerClient {

    private final RestClient restClient;

    public TestServerClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void sendTestReport() {
        restClient
                .post()
                .uri("/test-reporting")
                .body("payload")
                .retrieve()
                .toBodilessEntity();
    }
}
