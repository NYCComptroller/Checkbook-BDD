package io.reisys.checkbook.citywide.datafeeds.revenue;

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
	
	@When("^I navigate to Data Feeds Revenue$")
	public void navigateToDatafeedsRevenuePage() {
		RevenuePage.navigateToDatafeedsRevenuePage();
			
	}
	
	
	@Then("^I navigate to Citywide Revenue$")
	public void navigateToDatafeedscitywideRevenuePage(){
		RevenuePage.navigateToDatafeedscitywideRevenuePage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsCitywideRevenuePage(){
		RevenuePage.navigateToDatafeedsCitywideRevenueSubmit1();	
	}
	


	@And("^the System displays Citywide Revenue Transactions$")
    public void the_System_displays_citywide_Revenue_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getRevenueDetailsCount(2021,'B');
	assertFieldContainsText("Revenue  Data feeds Default Transaction Count", RevenuePage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Revenue Advanced search Title", RevenuePage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Revenue Transactions");
//	assertFieldContainsText("Revenue  Advanced Search Default Transactions Top navigation Amount", RevenuePage.getTransactionAmount() , RevenueAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

