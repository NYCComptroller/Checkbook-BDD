package io.reisys.checkbook.bdd.cucumber;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gson.JsonObject;

public class ExecutionContext {

	private static JsonObject jsonTestData;
	private static String dataProvided;
	private static String dataAsserted;
	private static String testRunFailures;
	private static SortedSet<BDDTestResult> testResults;
	
	public static void init(JsonObject jsonData) {
		jsonTestData = jsonData;
		testResults = new TreeSet<BDDTestResult>();
	}
	
	public static void cleanUp() {
		jsonTestData = new JsonObject();
	}
	
	public static JsonObject getJsonData() {
		return jsonTestData;
	}
	
	public static void resetStepData() {
		dataProvided = "";
		dataAsserted = "";
	}
	
	public static void resetTestRunFailure() {
		testRunFailures = "";
	}
	
	public static String getDataProvidedForStep() {
		return dataProvided;
	}
	
	public static String getDataAssertedForStep() {
		return dataAsserted;
	}
	
	public static String getTestRunFailures() {
		return testRunFailures;
	}
	
	public static void appendToStepData(String type, String locator, String value) {
		dataProvided += "Type: " + type + "\nLocator : " + locator + "\n "; 
		if (value != null) {
			dataProvided += "Value : " + value;
		}
		dataProvided +=  "\n\n";
	}
	
	public static void appendToAssertionData(String assertion) {
		dataAsserted += assertion + "\n\n";
	}
	
	public static void appendToTestRunFailure(String failure) {
		testRunFailures += failure;
	}
	
	public static void addTestResults(BDDTestResult testResult) {
		testResults.add(testResult);
	}

	public static Set<BDDTestResult> getTestResults() {
		return testResults;
	}
}
