package io.reisys.checkbook.nycedc.smartsearch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class SmartSearchSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	SmartSearchPage smartsearchPage;
	
	@Given("^I navigate to NYCEDC Smart Search Page$")
	public void navigateToAdvancedSearchPayrollFormPage() {
		    homePage.open();
		    smartsearchPage.navigateToOGEPage();
		    smartsearchPage.navigateToNYCEDCPage();   	
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCEDCSmartSearchResultsPage()  {
		smartsearchPage.navigateToSmartsearchPage();
			
	}
		

@Then("^the System displays NYCEDC Transactions$")
public void the_System_displays_Nycedc_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer smartSearchDetailsCountFromDB = DatabaseUtil.getNYCEDCTotalSpendingAllTransactionCount('B');
	assertFieldContainsText("NYCEDC  Smart search Default Transaction Count", smartsearchPage.getTotalCountForNycedcSmartSearch(), smartSearchDetailsCountFromDB.toString());
	assertFieldHasText("NYCEDC search Title", smartsearchPage.getSmartsearchNycedcTitle(), "Search Results NYCEDC");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}
	
	
@Then("^the System displays NYCEDC Spending Transactions$")
public void the_System_displays_nycha_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	//int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
	int year =2020;
	smartsearchPage.navigateToSmartsearchSpending();
	Integer smartSearchDetailsCountFromDB = DatabaseUtil.getNYCEDCTotalSpendingTransactionCount(year,'B');
	assertFieldContainsText("Spending Smart Search Default Transaction Count", smartsearchPage.getTotalCountForNycedcSmartSearch(), smartSearchDetailsCountFromDB.toString());
	assertFieldHasText("Spending Smart Search Title", smartsearchPage.getSmartsearchNycedcTitle(), "Search Results NYCEDC");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}

}
	

