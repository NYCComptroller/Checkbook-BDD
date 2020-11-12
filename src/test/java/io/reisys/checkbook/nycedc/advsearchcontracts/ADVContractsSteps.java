package io.reisys.checkbook.nycedc.advsearchcontracts;

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

public class ADVContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVContractsPage contractsPage;
	
	@Given("^I navigate to Advanced Search Contracts NYCEDC Page$")
	public void navigateToAdvancedSearchContractsFormPage() {
		    homePage.open();
		    contractsPage.navigateToOGEPage();
		    contractsPage.navigateToNYCEDCPage();
		    contractsPage.navigateToContractsPage();
		    contractsPage.navigateToAdvancedSearchfromContractsPage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCEDCContractsAdvancedSearchResultsPage()  {
		contractsPage.navigateToAdvancedSearchNYCEDCContractsSubmit();
			
	}
		

@Then("^the System displays NYCEDC Contracts Transactions$")
public void the_System_displays_Nycha_Contracts_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil3.getNYCEDCAEContractsAllTransactionCount('B');
	
	assertFieldHasText("Contracts Advanced search Title", contractsPage.getAdvancedSearchNYCEDCContractsDetailsTitle(), "NYCEDC Active Expense Contracts Transactions");
	assertFieldContainsText("Contracts  Advanced Search Default Transaction Count", contractsPage.getTotalCountForAdvancedSearchNYCEDCContractsDetails() , advancedSearchDetailsCountFromDB.toString());
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", spendingPage.getTransactionAmount() , SpendingAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

