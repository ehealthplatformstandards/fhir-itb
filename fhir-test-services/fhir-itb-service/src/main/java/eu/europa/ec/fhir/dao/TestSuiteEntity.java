package eu.europa.ec.fhir.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TestSuites")
public class TestSuiteEntity {

    @Id
    private int id;
    private String identifier;

    @Column(name = "fname")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "TestSuiteHasTestCases",
            joinColumns = @JoinColumn(name = "testsuite"),
            inverseJoinColumns = @JoinColumn(name = "testcase"))
    private List<TestCaseEntity> testCases;

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public List<TestCaseEntity> getTestCases() {
        return testCases;
    }
}
