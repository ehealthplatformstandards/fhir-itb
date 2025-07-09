package eu.europa.ec.fhir.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "testcases")
public class TestCaseEntity {

    @Id
    private int id;

    @ManyToMany(mappedBy = "testCases")
    private List<TestSuiteEntity> testSuites;

    public int getId() {
        return id;
    }
}
