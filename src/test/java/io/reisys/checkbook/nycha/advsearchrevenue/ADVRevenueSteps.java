package io.reisys.checkbook.nycha.advsearchrevenue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class ADVRevenueSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVRevenuePage RevenuePage;
	
	@Given("^I navigate to Advanced Search Revenue OGE Page$")
	public void navigateToAdvancedSearchRevenueFormPage() {
		    homePage.open();
		    RevenuePage.navigateToOGEPage();
		    RevenuePage.navigateToNYCHAPage();
		    RevenuePage.navigateToRevenuePage();
		    RevenuePage.navigateToAdvancedSearchfromRevenuePage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHARevenueAdvancedSearchResultsPage()  {
		RevenuePage.navigateToAdvancedSearchOGERevenueSubmit();
			
	}
	
	
	
@Then("^the System displays Nycha Revenue Transactions$")
public void the_System_displays_Nycha_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getNYCHARevenueDetailsCount(2020,'B');
	assertFieldContainsText("Revenue  Advanced Search Default Transaction Count", RevenuePage.getTotalCountForAdvancedSearchOGERevenueDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Revenue Advanced search Title", RevenuePage.getAdvancedSearchOGERevenueDetailsTitle(), "NYCHA Revenue Transactions");
//	assertFieldContainsText("Revenue  Advanced Search Default Transactions Top navigation Amount", RevenuePage.getTransactionAmount() , RevenueAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

