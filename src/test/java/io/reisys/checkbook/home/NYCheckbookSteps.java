package io.reisys.checkbook.home;

import cucumber.api.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;

public class NYCheckbookSteps extends ScenarioSteps {
	
	NYCheckbookPage checkbookPage;

	@When("^I select \"([^\"]*)\" for getting Spending data$")
	public void selectYear(String year) {
		checkbookPage.selectYear(year);
	}
}
