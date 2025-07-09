package eu.europa.ec.fhir.gitb.api.model;

import java.io.Serializable;

public final class TestSuiteSummary implements Serializable {
    private final String testSuiteId;
    private final String testSuiteName;
    private int passed;
    private int failed;
    private int undefined;
    private int notEvaluated;

    public TestSuiteSummary(
            String testSuiteId,
            String testSuiteName,
            int passed,
            int failed,
            int notEvaluated
    ) {
        this.testSuiteId = testSuiteId;
        this.testSuiteName = testSuiteName;
        this.passed = passed;
        this.failed = failed;
        this.notEvaluated = notEvaluated;
    }

    public String getTestSuiteId() {
        return testSuiteId;
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
}
