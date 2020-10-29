package io.reisys.checkbook.nycha.smartsearch;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class SmartSearchSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	SmartSearchPage smartsearchPage;
	
	@Given("^I navigate to Nycha Smart Search Page$")
	public void navigateToAdvancedSearchPayrollFormPage() {
		    homePage.open();
		    smartsearchPage.navigateToOGEPage();
		    smartsearchPage.navigateToNYCHAPage();   	
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHASmartSearchResultsPage()  {
		smartsearchPage.navigateToSmartsearchPage();
			
	}
		

@Then("^the System displays Nycha Transactions$")
public void the_System_displays_Nycha_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer smartSearchDetailsCountFromDB = DatabaseUtil.getNYCHAPayrollDetailsCount(2020,'C');
	assertFieldContainsText("Payroll  Advanced Search Default Transaction Count", smartsearchPage.getTotalCountForNychaSmartSearch(), smartSearchDetailsCountFromDB.toString());
	assertFieldHasText("Payroll Advanced search Title", smartsearchPage.getSmartsearchNychaTitle(), "Search Results NYCHA");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}
	
	
@Then("^the System displays Nycha Revenue Transactions$")
public void the_System_displays_nycha_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	smartsearchPage.navigateToSmartsearchRevenue();
	Integer smartSearchDetailsCountFromDB = DatabaseUtil.getNYCHARevenueDetailsCount(2020,'B');
	assertFieldContainsText("Revenue Smart Search Default Transaction Count", smartsearchPage.getTotalCountForNychaSmartSearch(), smartSearchDetailsCountFromDB.toString());
	assertFieldHasText("Revenue Smart Search Title", smartsearchPage.getSmartsearchNychaTitle(), "Search Results NYCHA");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}

}
	

