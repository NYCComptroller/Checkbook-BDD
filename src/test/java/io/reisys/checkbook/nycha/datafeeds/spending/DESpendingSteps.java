package io.reisys.checkbook.nycha.datafeeds.spending;

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

public class DESpendingSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFSpendingPage SpendingPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				SpendingPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Datafeeds Spending$")
	public void navigateToDatafeedsSpendingPage() {
		SpendingPage.navigateToDatafeedsSpendingPage();
			
	}
	
	
	@Then("^I navigate to NYCHA Spending$")
	public void navigateToDatafeedsNychaSpendingPage(){
		SpendingPage.navigateToDatafeedsNychaSpendingPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsNychaSpendingPage(){
		SpendingPage.navigateToDatafeedsNychaSpendingSubmit1();	
	}
	


	@And("^the System displays Nycha Spending Transactions$")
    public void the_System_displays_Nycha_Spending_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNYCHATotalSpendingAllTransactionCount('B');
	assertFieldContainsText("Spending  Data feeds Default Transaction Count", SpendingPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Spending Advanced search Title", SpendingPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Spending Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", SpendingPage.getTransactionAmount() , SpendingAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

