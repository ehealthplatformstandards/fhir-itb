package eu.europa.ec.fhir.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResultEntity, String> {
    List<TestResultEntity> findAllByOrderByDateTimeDesc();
}