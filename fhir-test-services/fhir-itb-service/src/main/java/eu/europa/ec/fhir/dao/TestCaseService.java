package eu.europa.ec.fhir.dao;

import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class TestCaseService {

    private final TestCaseRepository repository;

    public TestCaseService(TestCaseRepository repository) {
        this.repository = repository;
    }

    public String getLatestTestCaseIdentifier(String testCasePrefix) {
        return repository.findByIdentifierStartingWith(testCasePrefix)
                .stream()
                .map(TestCaseEntity::getIdentifier)
                .filter(identifier -> identifier.contains("_"))
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("No test case version found for " + testCasePrefix));
    }
}
