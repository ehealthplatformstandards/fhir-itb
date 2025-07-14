package eu.europa.ec.fhir.client;

import eu.europa.ec.fhir.gitb.api.model.TestResults;
import org.springframework.web.client.RestClient;

public class TestServerClient {

    private final RestClient restClient;

    public TestServerClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void sendTestReport(TestResults testResults) {
        restClient
                .post()
                .uri("/test-reporting")
                .body(testResults)
                .retrieve()
                .toBodilessEntity();
    }
}
