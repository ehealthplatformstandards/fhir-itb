package eu.europa.ec.fhir.gitb;

import eu.europa.ec.fhir.client.TestServerClient;
import eu.europa.ec.fhir.dao.TestCaseEntity;
import eu.europa.ec.fhir.dao.TestResultEntity;
import eu.europa.ec.fhir.dao.TestResultService;
import eu.europa.ec.fhir.dao.TestSuiteEntity;
import eu.europa.ec.fhir.dao.TestSuiteService;
import eu.europa.ec.fhir.gitb.api.model.TestCaseSummary;
import eu.europa.ec.fhir.gitb.api.model.TestResults;
import eu.europa.ec.fhir.gitb.api.model.TestSuiteSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static eu.europa.ec.fhir.gitb.api.model.TestCaseOutcome.FAILURE;
import static eu.europa.ec.fhir.gitb.api.model.TestCaseOutcome.SUCCESS;
import static eu.europa.ec.fhir.gitb.api.model.TestCaseOutcome.UNDEFINED;

@Service
public class ReportingService {

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private TestSuiteService testSuiteService;

    @Autowired
    private TestServerClient testServerClient;

    @Value("${TENANT_ID}")
    private int tenantId;

    @Value("${TENANT_API_KEY}")
    private String tenantApiKey;

    private static final Logger log = LoggerFactory.getLogger(ReportingService.class);

    private String latestTestCaseTimeStamp;

    @Scheduled(cron = "${testServer.reporting.cron:0 0 * * * *}")
    @Transactional
    public void report() {
        if(noNewTestResultsToReport()) {
            log.info("No new test results to report");
            return;
        }

        Set<String> testCasesCovered = new HashSet<>();
        latestTestCaseTimeStamp = testResultService.findLatestTestResultTimeStamp();
        Map<Integer, TestSuiteSummary> suiteToSummary = getTestSuiteSummaryMapBasedOnTestResults(testCasesCovered);
        addUnevaluatedTestCases(suiteToSummary, testCasesCovered);

        TestResults testResults = new TestResults(tenantId, tenantApiKey, latestTestCaseTimeStamp, suiteToSummary.values());
        testServerClient.sendTestReport(testResults);
        log.info("Report sent to Test server");
    }

    private boolean noNewTestResultsToReport() {
        if(latestTestCaseTimeStamp == null) {
            return false;
        }
        String actualLatestTestCaseTimeStamp = testResultService.findLatestTestResultTimeStamp();
        return latestTestCaseTimeStamp.equals(actualLatestTestCaseTimeStamp);
    }

    private Map<Integer, TestSuiteSummary> getTestSuiteSummaryMapBasedOnTestResults(Set<String> testCasesCovered) {
        Map<Integer, TestSuiteSummary> suiteToSummary = new HashMap<>();
        List<TestResultEntity> testResultEntities = testResultService.findAllTestResultsByOrderByDateTimeDesc();

        for(TestResultEntity testResultEntity : testResultEntities) {
            TestCaseEntity testcase = testResultEntity.getTestcase();
            String testcaseKey = testResultEntity.getTestsuite().getId() + "|" + testcase.getId();
            if(!testCasesCovered.contains(testcaseKey)) {
                testCasesCovered.add(testcaseKey);
                TestSuiteEntity testsuiteEntity = testResultEntity.getTestsuite();
                TestSuiteSummary testSuiteSummary = getTestSuiteSummaryOrNew(suiteToSummary, testsuiteEntity);
                String resultOutcome = testResultEntity.getResult().trim();
                if (resultOutcome.equals(SUCCESS.name())) {
                    testSuiteSummary.incrementPassed();
                } else if (resultOutcome.equals(FAILURE.name())) {
                    testSuiteSummary.incrementFailed();
                } else if (resultOutcome.equals(UNDEFINED.name())) {
                    testSuiteSummary.incrementUndefined();
                }
                testSuiteSummary.addTestCase(new TestCaseSummary(
                        testcase.getIdentifier(),
                        testcase.getName(),
                        testResultEntity.getDateTime(),
                        resultOutcome));
            }
        }

        return suiteToSummary;
    }

    private void addUnevaluatedTestCases(Map<Integer, TestSuiteSummary> suiteToSummary, Set<String> testCasesCovered) {
        List<TestSuiteEntity> testSuiteEntities = testSuiteService.getAllTestSuites();
        for(TestSuiteEntity testSuiteEntity : testSuiteEntities) {
            TestSuiteSummary testSuiteSummary = getTestSuiteSummaryOrNew(suiteToSummary, testSuiteEntity);
            for(TestCaseEntity testCaseEntity: testSuiteEntity.getTestCases()) {
                String testcaseKey = testSuiteEntity.getId() + "|" + testCaseEntity.getId();
                if(!testCasesCovered.contains(testcaseKey)) {
                    testCasesCovered.add(testcaseKey);
                    testSuiteSummary.incrementNotEvaluated();
                }
            }
        }
    }

    private static TestSuiteSummary getTestSuiteSummaryOrNew(Map<Integer, TestSuiteSummary> suiteToSummary, TestSuiteEntity testsuiteEntity) {
        TestSuiteSummary testSuiteSummary;
        if(!suiteToSummary.containsKey(testsuiteEntity.getId())) {
            testSuiteSummary = new TestSuiteSummary(testsuiteEntity.getIdentifier(), testsuiteEntity.getName(), 0, 0, 0);
            suiteToSummary.put(testsuiteEntity.getId(), testSuiteSummary);
            return testSuiteSummary;
        }
        return suiteToSummary.get(testsuiteEntity.getId());
    }
}
