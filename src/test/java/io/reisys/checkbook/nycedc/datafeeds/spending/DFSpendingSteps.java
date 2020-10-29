package io.reisys.checkbook.nycedc.datafeeds.spending;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class DFSpendingSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFSendingPage SpendingPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				SpendingPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Data Feeds Spending$")
	public void navigateToDatafeedsSpendingPage() {
		SpendingPage.navigateToDatafeedsSpendingPage();
			
	}
	
	
	@Then("^I navigate to NYCEDC Spending$")
	public void navigateToDatafeedsNYCEDCSpendingPage(){
		SpendingPage.navigateToDatafeedsNYCEDCSpendingPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsNYCEDCSpendingPage(){
		SpendingPage.navigateToDatafeedsNYCEDCSpendingSubmit1();	
	}
	


	@And("^the System displays NYCEDC Spending Transactions$")
    public void the_System_displays_NYCEDC_Spending_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNYCEDCTotalSpendingAllTransactionCount('B');
	assertFieldContainsText("Spending  Data feeds Default Transaction Count", SpendingPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Spending Advanced search Title", SpendingPage.getAdvancedSearchOGESpendingDetailsTitle(), "NYCHA Spending Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", SpendingPage.getTransactionAmount() , SpendingAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

