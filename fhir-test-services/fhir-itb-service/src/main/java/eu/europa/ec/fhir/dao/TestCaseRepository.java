package eu.europa.ec.fhir.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCaseEntity, String> {
    List<TestCaseEntity> findByIdentifierStartingWith(String testCasePrefix);
}