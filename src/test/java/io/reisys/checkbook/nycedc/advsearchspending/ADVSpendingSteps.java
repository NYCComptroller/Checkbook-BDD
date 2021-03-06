package io.reisys.checkbook.nycedc.advsearchspending;

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
import io.reisys.checkbook.utilities.DatabaseUtil3;

public class ADVSpendingSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVSpendingPage spendingPage;
	
	@Given("^I navigate to Advanced Search Spending NYCEDC Page$")
	public void navigateToAdvancedSearchSpendingFormPage() {
		    homePage.open();
		    spendingPage.navigateToOGEPage();
		    spendingPage.navigateToNYCEDCPage();
		    spendingPage.navigateToSpendingPage();
		    spendingPage.navigateToAdvancedSearchfromSpendingPage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCEDCSpendingAdvancedSearchResultsPage()  {
		spendingPage.navigateToAdvancedSearchNYCEDCSpendingSubmit();
			
	}
		

@Then("^the System displays NYCEDC Spending Transactions$")
public void the_System_displays_Nycha_Spending_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil3.getNYCEDCTotalSpendingTransactionCount('B');
	assertFieldContainsText("Spending  Advanced Search Default Transaction Count", spendingPage.getTotalCountForAdvancedSearchNYCEDCSpendingDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Spending Advanced search Title", spendingPage.getAdvancedSearchNYCEDCSpendingDetailsTitle(), "NYCHA Total Spending Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", spendingPage.getTransactionAmount() , SpendingAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

