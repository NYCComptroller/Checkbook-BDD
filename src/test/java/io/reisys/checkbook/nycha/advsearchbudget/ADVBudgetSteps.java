package io.reisys.checkbook.nycha.advsearchbudget;

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
import io.reisys.checkbook.utilities.DatabaseUtil2;

public class ADVBudgetSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVBudgetPage budgetPage;
	
	@Given("^I navigate to Advanced Search Budget NYCHA form Page$")
	public void navigateToAdvancedSearchbudgetFormPage() {
		    homePage.open();
				budgetPage.navigateToOGEPage();
				budgetPage.navigateToNYCHAPage();
				budgetPage.navigateToBudgetPage();
				budgetPage.navigateToAdvancedSearchfromBudgetPage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHAbudgetAdvancedSearchResultsPage()  {
		budgetPage.navigateToAdvancedSearchOGESubmit();
				}
	
	
@Then("^the System displays Nycha Budget Transactions$")
public void the_System_displays_Nycha_Budget_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil2.getNYCHABudgetDetailsCount1(2020,'B');
	assertFieldContainsText("budget  Advanced Search Default Transaction Count", budgetPage.getTotalCountForAdvancedSearchOGEBudgetDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("budget Advanced search Title", budgetPage.getAdvancedSearchOGEBudgetDetailsTitle(), "NYCHA Expense Budget Transactions");
//	assertFieldContainsText("budget  Advanced Search Default Transactions Top navigation Amount", budgetPage.getTransactionAmount() , budgetAmount);
	assertEquals( budgetPage.getTotalCountForAdvancedSearchOGEBudgetDetails() , advancedSearchDetailsCountFromDB.toString());
	softAssertion.assertAll();
}
	


	
}
	

