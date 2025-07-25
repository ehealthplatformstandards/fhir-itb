package eu.europa.ec.fhir.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TestCases")
public class TestCaseEntity {

    @Id
    private int id;

    @ManyToMany(mappedBy = "testCases")
    private List<TestSuiteEntity> testSuites;

    private String identifier;

    @Column(name = "fname")
    private String name;

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}
