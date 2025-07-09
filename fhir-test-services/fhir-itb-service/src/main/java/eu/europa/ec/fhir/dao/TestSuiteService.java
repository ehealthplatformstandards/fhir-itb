package eu.europa.ec.fhir.dao;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSuiteService {

    private final TestSuiteRepository repository;

    public TestSuiteService(TestSuiteRepository repository) {
        this.repository = repository;
    }

    public List<TestSuiteEntity> getAllTestSuites() {
        return repository.findAll();
    }
}
