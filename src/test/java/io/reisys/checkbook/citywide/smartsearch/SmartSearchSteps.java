package io.reisys.checkbook.citywide.smartsearch;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class SmartSearchSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	SmartSearchPage smartsearchPage;
	
	@Given("^I navigate to Citywide Smart Search Page$")
	public void navigateToAdvancedSearchPayrollFormPage() {
		    homePage.open();
		       	
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHASmartSearchResultsPage()  {
		smartsearchPage.navigateToSmartsearchPage();
			
	}
		

@Then("^the System displays Citywide Transactions$")
public void the_System_displays_Citywide_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer smartSearchDetailsCountFromDB = DatabaseUtil.getCitywideRevenueAllTransactionCount('B');
	assertFieldContainsText("Revenue  Advanced Search Default Transaction Count", smartsearchPage.getTotalCountForCitywideSmartSearch(), smartSearchDetailsCountFromDB.toString());
	assertFieldHasText("Revenue Advanced search Title", smartsearchPage.getSmartsearchCitywideTitle(), "Search Results Citywide");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}
	
	
@Then("^the System displays Citywide Revenue Transactions$")
public void the_System_displays_Citywide_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	smartsearchPage.navigateToSmartsearchRevenue();
	Integer smartSearchDetailsCountFromDB = DatabaseUtil.getCitywideRevenueTransactionCount('B');
	assertFieldContainsText("Revenue Smart Search Default Transaction Count", smartsearchPage.getTotalCountForCitywideSmartSearch(), smartSearchDetailsCountFromDB.toString());
	assertFieldHasText("Revenue Smart Search Title", smartsearchPage.getSmartsearchCitywideTitle(), "Search Results Citywide");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}

}
	

