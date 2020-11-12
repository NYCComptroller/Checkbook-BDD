package io.reisys.checkbook.citywide.advsearchrevenue;

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

public class ADVRevenueSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVRevenuePage revenuePage;
	
	@Given("^I navigate to Advanced Search Citywide Revenue Page$")
	public void navigateToAdvancedSearchSpendingFormPage() {
		    homePage.open();
		    revenuePage.navigateToRevenuePage();
		    revenuePage.navigateToAdvancedSearchfromRevenuePage();		
			}
	
	@When("^I click on Submit$")
	public void navigateToCitywideRevenueAdvancedSearchResultsPage()  {
		revenuePage.navigateToAdvancedSearchRevenueSubmit();
			
	}
		
@Then("^the System displays Citywide Revenue Transactions$")
public void the_System_displays_Citywide_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getCitywideRevenueTransactionCount('B');
	assertFieldContainsText("Revenue  Advanced Search Default Transaction Count", revenuePage.getTotalCountForAdvancedSearchCitywideRevenueDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Revenue Advanced search Title", revenuePage.getAdvancedSearchCitywideRevenueDetailsTitle(), "Expense Budget Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", spendingPage.getTransactionAmount() , SpendingAmount);
	assertEquals( revenuePage.getTotalCountForAdvancedSearchCitywideRevenueDetails() , advancedSearchDetailsCountFromDB.toString());
	softAssertion.assertAll();
}
	
	
}
	

