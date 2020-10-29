package io.reisys.checkbook.nycha.datafeeds.budget;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class DFBudgetSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFBudgetPage BudgetPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				BudgetPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Datafeeds Budget$")
	public void navigateToDatafeedsBudgetPage() {
		BudgetPage.navigateToDatafeedsBudgetPage();
			
	}
	
	
	@Then("^I navigate to NYCHA Budget$")
	public void navigateToDatafeedsNychaBudgetPage(){
		BudgetPage.navigateToDatafeedsNychaBudgetPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsNychaBudgetPage(){
		BudgetPage.navigateToDatafeedsNychaBudgetSubmit1();	
	}
	


	@And("^the System displays Nycha Budget Transactions$")
    public void the_System_displays_Nycha_Budget_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNYCHABudgetDetailsCount(2020,'B');
	assertFieldContainsText("Budget  Data feeds Default Transaction Count", BudgetPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Budget Advanced search Title", BudgetPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Budget Transactions");
//	assertFieldContainsText("Budget  Advanced Search Default Transactions Top navigation Amount", BudgetPage.getTransactionAmount() , BudgetAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

