package eu.europa.ec.fhir.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResultEntity, String> {
    List<TestResultEntity> findAllByTestcaseIsNotNullOrderByDateTimeDesc();

    @Query("SELECT MAX(e.dateTime) FROM TestResultEntity e WHERE e.dateTime IS NOT NULL")
    String findLatestTestResultTimeStamp();
}