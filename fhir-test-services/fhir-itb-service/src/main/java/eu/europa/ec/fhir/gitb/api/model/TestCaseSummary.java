package eu.europa.ec.fhir.gitb.api.model;

import java.io.Serializable;

public record TestCaseSummary(String testCaseId, String testCaseName, String timeStamp, String outcome) implements Serializable { }
