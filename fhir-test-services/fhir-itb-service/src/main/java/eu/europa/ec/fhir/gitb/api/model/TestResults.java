package eu.europa.ec.fhir.gitb.api.model;

import java.io.Serializable;
import java.util.Collection;

public record TestResults (
        int tenantId,
        String tenantApiKey,
        String latestTestTimeStamp,
        Collection<TestSuiteSummary> testSuiteResults
) implements Serializable {}