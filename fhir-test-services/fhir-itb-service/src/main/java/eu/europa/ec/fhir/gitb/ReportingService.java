package eu.europa.ec.fhir.gitb;

import eu.europa.ec.fhir.client.TestServerClient;
import eu.europa.ec.fhir.dao.TestResult;
import eu.europa.ec.fhir.dao.TestResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private TestServerClient testServerClient;

    private static final Logger log = LoggerFactory.getLogger(ReportingService.class);

    public void report() {
        List<TestResult> results = testResultService.getAllResults();
        log.info("Result size: {}", results.size());
        testServerClient.sendTestReport();
        log.info("Report sent to Test server");
    }
}
