package eu.europa.ec.fhir.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "testresults")
public class TestResultEntity {

    @Id
    @Column(name = "test_session_id")
    private String testSessionId;

    @ManyToOne
    @JoinColumn(name = "testsuite_id")
    private TestSuiteEntity testsuite;
    private int testcaseId;
    private String testcase;
    private String result;
    @Column(name = "end_time")
    private String dateTime; // iso 8601

    public TestSuiteEntity getTestsuite() {
        return testsuite;
    }

    public void setTestsuite(TestSuiteEntity testsuite) {
        this.testsuite = testsuite;
    }

    public int getTestcaseId() {
        return testcaseId;
    }

    public String getResult() {
        return result;
    }
}
