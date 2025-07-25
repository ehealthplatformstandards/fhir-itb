package eu.europa.ec.fhir.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "TestResults")
public class TestResultEntity {

    @Id
    @Column(name = "test_session_id")
    private String testSessionId;

    @ManyToOne
    @JoinColumn(name = "testsuite_id")
    private TestSuiteEntity testsuite;
    @ManyToOne
    @JoinColumn(name = "testcase_id")
    private TestCaseEntity testcase;
    private String result;
    @Column(name = "end_time")
    private String dateTime; // iso 8601

    public TestSuiteEntity getTestsuite() {
        return testsuite;
    }

    public String getResult() {
        return result;
    }

    public TestCaseEntity getTestcase() {
        return testcase;
    }

    public String getDateTime() {
        return dateTime;
    }
}
