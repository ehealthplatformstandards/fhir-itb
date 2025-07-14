package eu.europa.ec.fhir.handlers;

import eu.europa.ec.fhir.client.ItbRestClient;
import eu.europa.ec.fhir.client.TestServerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Configuration
public class HandlersConfig {
    @Bean
    HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Value("${itb.vendor.api_key}")
    private String ORG_API_KEY;

    @Value("${itb.base_url}")
    private String ITB_BASE_URL;

    @Value("${testServer.base_url}")
    private String TEST_SERVER_BASE_URL;

    @Bean
    ItbRestClient itbRestClient() {
        var restClient = RestClient.builder()
                .baseUrl(ITB_BASE_URL + "/api/rest")
                .defaultHeader("ITB_API_KEY", ORG_API_KEY)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();

        return new ItbRestClient(restClient);
    }

    @Bean
    TestServerClient testServerClient() {
        var restClient = RestClient.builder()
                .baseUrl(TEST_SERVER_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();

        return new TestServerClient(restClient);
    }

    @Bean
    RestClient restClient() {
        return RestClient.builder().build();
    }

}
