package io.reisys.checkbook.bdd.cucumber;

import java.util.Comparator;
import java.util.Objects;

public class BDDStepExecutionResult implements Comparable<BDDStepExecutionResult> {

	private String id;
	private String scenarioName;
	private String featureFile;
	private String featureDescription;
	private String stepName;
	private String stepResult;
	private String timeTaken;
	private String errorMessage;
	private String screenshotFileName;
	private static final String COMMA_DELIMITER = ",";
	private static final String LINE_SEPARATOR = "\r\n";
	
	public BDDStepExecutionResult () {}
	
	public BDDStepExecutionResult(String id, String scenarioName, String featureFile, String featureDescription, 
			String stepName, String stepResult, String timeTaken, String errorMessage, String screenshotFileName) {
		this.id = id;
		this.scenarioName = scenarioName;
		this.featureFile = featureFile;
		this.featureDescription = featureDescription;
		this.stepName = stepName;
		this.stepResult = stepResult;
		this.timeTaken = timeTaken;
		this.errorMessage = errorMessage;
		this.screenshotFileName = screenshotFileName;
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
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getScreenshotFileName() {
		return screenshotFileName;
	}
	public void setScreenshotFileName(String screenshotFileName) {
		this.screenshotFileName = screenshotFileName;
	}
	public String getStepResult() {
		return stepResult;
	}
	public void setStepResult(String stepResult) {
		this.stepResult = stepResult;
	}

	public String toString() {
		String resultDetails = featureDescription + COMMA_DELIMITER + featureFile + COMMA_DELIMITER + scenarioName + COMMA_DELIMITER + stepName + COMMA_DELIMITER + stepResult
				+ COMMA_DELIMITER + timeTaken + COMMA_DELIMITER;
		resultDetails += "=HYPERLINK(\"" + screenshotFileName + "\")" + COMMA_DELIMITER;
		resultDetails += "" + COMMA_DELIMITER + "" + LINE_SEPARATOR;
		return resultDetails;
	}
	
	@Override
    public boolean equals ( Object o ) {
        if ( o == this ) return true;
        if (!(o instanceof BDDStepExecutionResult)) return false;
        BDDStepExecutionResult result = (BDDStepExecutionResult) o;
        return Objects.equals(id, result.id) && stepName.equals(result.getStepName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stepName);
    }

	@Override
	public int compareTo(BDDStepExecutionResult o) {
		return Comparator.comparing(BDDStepExecutionResult::getId)
	        .thenComparing(BDDStepExecutionResult::getStepName)
	        .compare(this, o);
	}
}
