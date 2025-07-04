package eu.europa.ec.fhir.dao;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "testresults")
public class TestResult {

    @Id
    @Column(name = "test_session_id")
    private String testSessionId;

    private String sutId;
    private String sut;
    private String organizationId;
    private String organization;
    private String communityId;
    private String community;
    private String domainId;

    @Column(name = "domain")
    private String domainName;

    private String specificationId;
    private String specification;
    private String actorId;
    private String actor;
    private String testsuiteId;
    private String testsuite;
    private String testcaseId;
    private String testcase;

    private String result;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String outputMessage;
}
