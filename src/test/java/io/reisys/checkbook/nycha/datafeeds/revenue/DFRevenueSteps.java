package io.reisys.checkbook.nycha.datafeeds.revenue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class DFRevenueSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFRevenuePage RevenuePage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				RevenuePage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Datafeeds Revenue$")
	public void navigateToDatafeedsRevenuePage() {
		RevenuePage.navigateToDatafeedsRevenuePage();
			
	}
	
	
	@Then("^I navigate to NYCHA Revenue$")
	public void navigateToDatafeedsNychaRevenuePage(){
		RevenuePage.navigateToDatafeedsNychaRevenuePage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsNychaRevenuePage(){
		RevenuePage.navigateToDatafeedsNychaRevenueSubmit1();	
	}
	


	@And("^the System displays Nycha Revenue Transactions$")
    public void the_System_displays_Nycha_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNYCHARevenueDetailsCount(2020,'B');
	assertFieldContainsText("Revenue  Data feeds Default Transaction Count", RevenuePage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Revenue Advanced search Title", RevenuePage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Revenue Transactions");
//	assertFieldContainsText("Revenue  Advanced Search Default Transactions Top navigation Amount", RevenuePage.getTransactionAmount() , RevenueAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

