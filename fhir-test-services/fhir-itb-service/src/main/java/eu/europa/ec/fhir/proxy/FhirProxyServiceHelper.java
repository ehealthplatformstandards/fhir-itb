package eu.europa.ec.fhir.proxy;

import eu.europa.ec.fhir.http.HttpUtils;
import eu.europa.ec.fhir.http.RequestParams;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FhirProxyServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FhirProxyServiceHelper.class);

    @Value("${fhir.proxy.endpoint}")
    private String fhirProxyEndpoint;

    private URI buildFhirURI(HttpServletRequest request, String baseUrl, String path) {
        String queryStringFiltered = HttpUtils.getQueryString(request)
                .map(queryString -> queryString.substring(1))
                .map(q -> Arrays.stream(q.split("&"))
                        .filter(p -> !p.contains("testSuite=")) // remove testSuite query param meant only for ITB
                        .collect(Collectors.joining("&", "?", ""))

                ).filter(q -> !q.trim().equals("?")).orElse("");
        String uriString = String.format("%s/%s%s", baseUrl, path, queryStringFiltered);

        try {
            return new URL(uriString).toURI();
        } catch (Exception e) {
            LOGGER.error("Invalid Proxy URI: {}", uriString);
            throw new IllegalArgumentException("Invalid URI: " + uriString);
        }
    }

    public RequestParams toFhirHttpParams(HttpServletRequest request, String path, Optional<String> body) {
        return new RequestParams(
                buildFhirURI(request, fhirProxyEndpoint, path),
                HttpMethod.valueOf(request.getMethod()),
                HttpUtils.cloneHeaders(request),
                body
        );
    }
}
