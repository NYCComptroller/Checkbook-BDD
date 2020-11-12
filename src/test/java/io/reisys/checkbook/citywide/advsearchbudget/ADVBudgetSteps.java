package io.reisys.checkbook.citywide.advsearchbudget;

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

public class ADVBudgetSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVBudgetPage budgetPage;
	
	@Given("^I navigate to Advanced Search Citywide Budget Page$")
	public void navigateToAdvancedSearchSpendingFormPage() {
		    homePage.open();
		    budgetPage.navigateToSpendingPage();
		    budgetPage.navigateToAdvancedSearchfromBudgetPage();		
			}
	
	@When("^I click on Submit$")
	public void navigateToCitywideBudgetAdvancedSearchResultsPage()  {
		budgetPage.navigateToAdvancedSearchBudgetSubmit();
			
	}
		
@Then("^the System displays Citywide Budget Transactions$")
public void the_System_displays_Citywide_Budget_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getCitywideBudgetTransactionCount('B');
	assertFieldContainsText("Budget  Advanced Search Default Transaction Count", budgetPage.getTotalCountForAdvancedSearchCitywideBudgetDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Budget Advanced search Title", budgetPage.getAdvancedSearchCitywideBudgetDetailsTitle(), "Expense Budget Transactions");

	softAssertion.assertAll();
}
	
	
}
	

