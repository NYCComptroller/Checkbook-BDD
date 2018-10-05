package io.reisys.checkbook.bdd.cucumber;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;

public class StepDataListener implements StepListener {
	private static final Logger LOGGER = Logger.getLogger(StepDataListener.class.getName()) ;
	
	public void testSuiteStarted(Class<?> storyClass) {
	}

	public void testSuiteStarted(Story story) {
		ExecutionContext.resetTestRunFailure();
	}

	public void testSuiteFinished() {
		if (!ExecutionContext.getTestResults().isEmpty()) {
			LOGGER.info("------ Creating test results file");
			File file = new File("target/site/serenity/BDDResults.csv");
	        try {
	        	FileWriter writer = new FileWriter(file, true);
	        	if (file.length() < 1 ) {
	        		writer.append("FEATURE DESCRIPTION , FEATURE FILE, SCENARIO NAME, RESULT, STEP COUNT, DURATION, HTML REPORT, FAILED STEP, ERROR MESSAGE\r\n");
	        	}
	        	for(BDDTestResult result: ExecutionContext.getTestResults()) {
	        		writer.append(result.toString());
	        	}
	        	writer.close();
			} catch (IOException e) {
				LOGGER.warning(e.getMessage());
			}
		}		
	}

	public void testStarted(String description) {

	}

	public void testStarted(String description, String id) {

	}

	public void testFinished(TestOutcome result) {
		BDDTestResult testResult = new BDDTestResult();
		
		testResult.setId(result.getId());
		testResult.setScenarioName(result.getTitle());
		testResult.setFeatureFile(result.getPath());
		testResult.setFeatureDescription(result.getStoryTitle());
		testResult.setStepCount(result.getStepCount());
		testResult.setTimeTaken(String.valueOf(result.getDurationInSeconds()));
		testResult.setHtmlReport(result.getHtmlReport());
		testResult.setTestResult(result.getResult().toString());
		if (result.getSuccessCount() != result.getTestStepCount()) {
			testResult.setFailedStep(result.getFailingStep().get().getDescription());
			testResult.setErrorMessage(result.getErrorMessage());
		}
		ExecutionContext.addTestResults(testResult);		
	}

	public void testRetried() {

	}

	public void stepStarted(ExecutedStepDescription description) {
		ExecutionContext.resetStepData();
	}

	public void skippedStepStarted(ExecutedStepDescription description) {

	}

	public void stepFailed(StepFailure failure) {
		addAdditionalStepData();
	}

	public void lastStepFailed(StepFailure failure) {

	}

	public void stepIgnored() {

	}

	public void stepPending() {

	}

	public void stepPending(String message) {

	}

	public void stepFinished() {
		addAdditionalStepData();
	}
	
	private void addAdditionalStepData() {
		if (ExecutionContext.getDataProvidedForStep().length() > 0 && ExecutionContext.getDataAssertedForStep().length() > 0) {
			Serenity.recordReportData().withTitle("Data Provided & Assertions")
				.andContents("Data Provided : \n\n" + ExecutionContext.getDataProvidedForStep() + "\n\n" +
							 "Data Assertions : \n\n" + ExecutionContext.getDataAssertedForStep()
						);
		} else if (ExecutionContext.getDataProvidedForStep().length() > 0) {
	    	Serenity.recordReportData().withTitle("Data Provided")
				.andContents(ExecutionContext.getDataProvidedForStep());
    	} else if (ExecutionContext.getDataAssertedForStep().length() > 0) {
	    	Serenity.recordReportData().withTitle("Data Asssertions")
				.andContents(ExecutionContext.getDataAssertedForStep());
    	}
	}

	public void testFailed(TestOutcome testOutcome, Throwable cause) {
	}

	public void testIgnored() {

	}

	public void testSkipped() {

	}

	public void testPending() {

	}

	public void testIsManual() {

	}

	public void notifyScreenChange() {

	}

	public void useExamplesFrom(DataTable table) {
	}

	public void addNewExamplesFrom(DataTable table) {

	}

	public void exampleStarted(Map<String, String> data) {

	}

	public void exampleFinished() {

	}

	public void assumptionViolated(String message) {

	}

	public void testRunFinished() {
	}

}
