package io.reisys.checkbook.citywide.advsearchcontracts;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class ADVContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVContractsPage contractsPage;
	
	@Given("^I navigate to Advanced Search Citywide Contracts Page$")
	public void navigateToAdvancedSearchSpendingFormPage() {
		    homePage.open();
		    contractsPage.navigateToSpendingPage();
		    contractsPage.navigateToAdvancedSearchfromContractsPage();		
			}
	
	@When("^I click on Submit$")
	public void navigateToNYCHASpendingAdvancedSearchResultsPage()  {
		contractsPage.navigateToAdvancedSearchContractsSubmit();
			
	}
		
@Then("^the System displays Citywide Contracts Transactions$")
public void the_System_displays_Nycha_Spending_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getCitywideAEContractsAllTransactionCount('B');
	assertFieldContainsText("Contracts  Advanced Search Default Transaction Count", contractsPage.getTotalCountForAdvancedSearchCitywideContractsDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Contracts Advanced search Title", contractsPage.getAdvancedSearchCitywideContractsDetailsTitle(), "Active Expense Contracts Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", spendingPage.getTransactionAmount() , SpendingAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

