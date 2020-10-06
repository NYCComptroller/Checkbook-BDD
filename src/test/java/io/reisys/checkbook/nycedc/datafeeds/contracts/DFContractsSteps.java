package io.reisys.checkbook.nycedc.datafeeds.contracts;

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

public class DFContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFContractsPage ContractsPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				ContractsPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Data Feeds Contracts$")
	public void navigateToDatafeedsContractsPage() {
		ContractsPage.navigateToDatafeedsContractsPage();
			
	}
	
	
	@Then("^I navigate to NYCEDC Contracts$")
	public void navigateToDatafeedscitywideContractsPage(){
		ContractsPage.navigateToDatafeedsNYCEDCContractsPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsNYCEDCContractsPage(){
		ContractsPage.navigateToDatafeedsNYCEDCContractsSubmit1();	
	}
	


	@And("^the System displays NYCEDC Contracts Transactions$")
    public void the_System_displays_citywide_Contracts_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNYCEDCAEContractsAllTransactionCount('B');
	assertFieldContainsText("Contracts  Data feeds Default Transaction Count", ContractsPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Contracts Advanced search Title", ContractsPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Contracts Transactions");
//	assertFieldContainsText("Contracts  Advanced Search Default Transactions Top navigation Amount", ContractsPage.getTransactionAmount() , ContractsAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

