package eu.europa.ec.fhir.dao;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultService {

    private final TestResultRepository repository;

    public TestResultService(TestResultRepository repository) {
        this.repository = repository;
    }

    public List<TestResult> getAllResults() {
        return repository.findAll();
    }
}
