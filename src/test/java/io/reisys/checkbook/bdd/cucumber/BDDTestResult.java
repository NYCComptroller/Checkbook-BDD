package io.reisys.checkbook.bdd.cucumber;

import java.util.Objects;

import org.apache.commons.lang.StringEscapeUtils;

public class BDDTestResult implements Comparable<BDDTestResult> {

	private String id;
	private String scenarioName;
	private String featureFile;
	private String featureDescription;
	private String testResult;
	private String timeTaken;
	private String errorMessage;
	private String failedStep;
	private String htmlReport;
	private int stepCount;
	private static final String COMMA_DELIMITER = ",";
	private static final String LINE_SEPARATOR = "\r\n";
	
	BDDTestResult () { }
	
	public BDDTestResult(String id, String scenarioName, String featureFile, String featureDescription, 
			int stepCount, String testResult, String timeTaken, String errorMessage, String failedStep, String htmlReport) {
		this.id=id;
		this.scenarioName = scenarioName;
		this.featureFile = featureFile;
		this.featureDescription = featureDescription;
		this.stepCount = stepCount;
		this.testResult = testResult;
		this.timeTaken = timeTaken;
		this.errorMessage = errorMessage;
		this.failedStep = failedStep;
		this.htmlReport = htmlReport;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getFeatureFile() {
		return featureFile;
	}

	public void setFeatureFile(String featureFile) {
		this.featureFile = featureFile;
	}

	public String getFeatureDescription() {
		return featureDescription;
	}

	public void setFeatureDescription(String featureDescription) {
		this.featureDescription = featureDescription;
	}

	public int getStepCount() {
		return stepCount;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFailedStep() {
		return failedStep;
	}

	public void setFailedStep(String failedStep) {
		this.failedStep = failedStep;
	}

	public String getHtmlReport() {
		return htmlReport;
	}

	public void setHtmlReport(String htmlReport) {
		this.htmlReport = htmlReport;
	}
	
	public String toString() {
		String resultDetails = featureDescription + COMMA_DELIMITER + featureFile + COMMA_DELIMITER + scenarioName + COMMA_DELIMITER + testResult + COMMA_DELIMITER
				+ stepCount + COMMA_DELIMITER + timeTaken + COMMA_DELIMITER;
		resultDetails += "=HYPERLINK(\"" + htmlReport + "\")" + COMMA_DELIMITER;
		
		if ( !"SUCCESS".equals(testResult) ) resultDetails += failedStep + COMMA_DELIMITER  + StringEscapeUtils.escapeCsv(errorMessage) +LINE_SEPARATOR;
		else resultDetails += "" + COMMA_DELIMITER + "" + LINE_SEPARATOR;
		return resultDetails;
	}
	
	@Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !(o instanceof BDDTestResult) ) return false;
        BDDTestResult result = (BDDTestResult) o;
        return Objects.equals(id, result.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

	@Override
	public int compareTo(BDDTestResult o) { return id.compareTo(o.getId()); }
}
