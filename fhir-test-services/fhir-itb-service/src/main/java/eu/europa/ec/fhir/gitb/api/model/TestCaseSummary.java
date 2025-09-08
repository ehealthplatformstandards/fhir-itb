package eu.europa.ec.fhir.gitb.api.model;

import java.io.Serializable;

public record TestCaseSummary(String testCaseUid, String testCaseName, String timeStamp, String outcome) implements Serializable { }
