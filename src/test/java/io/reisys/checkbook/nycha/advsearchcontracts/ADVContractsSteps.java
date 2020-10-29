package io.reisys.checkbook.nycha.advsearchcontracts;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class ADVContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVContractsPage contractsPage;
	
	@Given("^I navigate to Advanced Search Contracts OGE Page$")
	public void navigateToAdvancedSearchContractsFormPage() {
		    homePage.open();
				contractsPage.navigateToOGEPage();
				contractsPage.navigateToNYCHAPage();
				contractsPage.navigateToContractsPage();
				contractsPage.navigateToAdvancedSearchfromContractsPage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHAContractsAdvancedSearchResultsPage()  {
		contractsPage.navigateToAdvancedSearchOGEContractsSubmit();
			
	}	


@Then("^the System displays Nycha Contracts Transactions$")
public void the_System_displays_Nycha_Contracts_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getNychaContractsAllDetailsCount('B');
	assertFieldContainsText("Contracts  Advanced Search Default Transaction Count", contractsPage.getTotalCountForAdvancedSearchOGEContractsDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Contracts Advanced search Title", contractsPage.getAdvancedSearchOGEContractsDetailsTitle(), "NYCHA Contracts Transactions");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

