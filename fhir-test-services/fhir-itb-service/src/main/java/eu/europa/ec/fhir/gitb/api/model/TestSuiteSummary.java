package eu.europa.ec.fhir.gitb.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class TestSuiteSummary implements Serializable {
    private final String testSuiteUid;
    private final String testSuiteName;
    private int passed;
    private int failed;
    private int undefined;
    private int notEvaluated;
    private final List<TestCaseSummary> testCases;

    public TestSuiteSummary(
            String testSuiteUid,
            String testSuiteName,
            int passed,
            int failed,
            int notEvaluated
    ) {
        this.testSuiteUid = testSuiteUid;
        this.testSuiteName = testSuiteName;
        this.passed = passed;
        this.failed = failed;
        this.notEvaluated = notEvaluated;
        this.testCases = new ArrayList<>();
    }

    public String getTestSuiteUid() {
        return testSuiteUid;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public int getPassed() {
        return passed;
    }

    public int getFailed() {
        return failed;
    }

    public int getUndefined() {
        return undefined;
    }

    public int getNotEvaluated() {
        return notEvaluated;
    }

    public void incrementPassed() {
        passed++;
    }

    public void incrementFailed() {
        failed++;
    }

    public void incrementUndefined() {
        undefined++;
    }

    public void incrementNotEvaluated() {
        notEvaluated++;
    }

    public List<TestCaseSummary> getTestCases() {
        return testCases;
    }

    public void addTestCase(TestCaseSummary testCase) {
        this.testCases.add(testCase);
    }
}
