package io.reisys.checkbook.bdd.cucumber;

/**
 * BDD Results Summary class
 */
public class BDDResultsSummary {
	
	private String featureName;
	private int totalScenariosCount;
	private int successScenariosCount;
	private int failedScenariosCount;
	private int errorScenariosCount;
	
	public BDDResultsSummary(String featureName) {
		this.featureName = featureName;
	}
	
	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public int getTotalScenariosCount() {
		return totalScenariosCount;
	}

	public void setTotalScenariosCount(int totalScenariosCount) {
		this.totalScenariosCount = totalScenariosCount;
	}
	
	public void addTotalScenarioCount() {
		totalScenariosCount ++;
	}
	
	public int getSuccessScenariosCount() {
		return successScenariosCount;
	}

	public void setSuccessScenariosCount(int successScenariosCount) {
		this.successScenariosCount = successScenariosCount;
	}
	
	public void addSuccessScenarioCount() {
		successScenariosCount ++;
	}

	public int getFailedScenariosCount() {
		return failedScenariosCount;
	}

	public void setFailedScenariosCount(int failedScenariosCount) {
		this.failedScenariosCount = failedScenariosCount;
	}

	public void addFailedScenarioCount() {
		failedScenariosCount ++;
	}
	
	public int getErrorScenariosCount() {
		return errorScenariosCount;
	}

	public void setErrorScenariosCount(int errorScenariosCount) {
		this.errorScenariosCount = errorScenariosCount;
	}

	public void addErrorScenarioCount() {
		errorScenariosCount ++;
	}
	
	public String toString() {
		return featureName + "," + totalScenariosCount + "," + successScenariosCount + "," + failedScenariosCount + "," + errorScenariosCount;
	}
}
