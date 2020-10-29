package io.reisys.checkbook.nycha.advsearchspending;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class ADVSpendingSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVSpendingPage spendingPage;
	
	@Given("^I navigate to Advanced Search Spending OGE Page$")
	public void navigateToAdvancedSearchSpendingFormPage() {
		    homePage.open();
		    spendingPage.navigateToOGEPage();
		    spendingPage.navigateToNYCHAPage();
		    spendingPage.navigateToSpendingPage();
		    spendingPage.navigateToAdvancedSearchfromSpendingPage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHASpendingAdvancedSearchResultsPage()  {
		spendingPage.navigateToAdvancedSearchOGESpendingSubmit();
			
	}
		

@Then("^the System displays Nycha Spending Transactions$")
public void the_System_displays_Nycha_Spending_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getNYCHATotalSpendingAllTransactionCount('B');
	assertFieldContainsText("Spending  Advanced Search Default Transaction Count", spendingPage.getTotalCountForAdvancedSearchOGESpendingDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Spending Advanced search Title", spendingPage.getAdvancedSearchOGESpendingDetailsTitle(), "NYCHA Total Spending Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", spendingPage.getTransactionAmount() , SpendingAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

