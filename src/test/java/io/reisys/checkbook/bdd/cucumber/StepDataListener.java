package io.reisys.checkbook.bdd.cucumber;

import java.util.Map;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;

public class StepDataListener implements StepListener {

	public void testSuiteStarted(Class<?> storyClass) {
	}

	public void testSuiteStarted(Story story) {
		ExecutionContext.resetTestRunFailure();
	}

	public void testSuiteFinished() {
	}

	public void testStarted(String description) {

	}

	public void testStarted(String description, String id) {

	}

	public void testFinished(TestOutcome result) {
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
