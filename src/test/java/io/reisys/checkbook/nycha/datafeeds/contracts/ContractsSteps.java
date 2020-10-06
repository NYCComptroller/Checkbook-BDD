package io.reisys.checkbook.nycha.datafeeds.contracts;

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

public class ContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ContractsPage contractsPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
		    contractsPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Datfeeds Contracts$")
	public void navigateToDatafeedsContractsPage() {
		contractsPage.navigateToDatafeedsContractsPage();
			
	}
	
	@Then("^I navigate to NYCHA Contracts$")
	public void navigateToNychaContractsPage() {
		contractsPage.navigateToDatafeedsNychaContractsPage();
			
	}
		

	@And("^I submit the form$")
	public void submitToDatafeedsNychaPayrollPage(){
		contractsPage.navigateToDatafeedsNychaContractsSubmit1();	
	}
	


	@And("^the System displays Nycha Contracts Transactions$")
    public void the_System_displays_Nycha_Contracts_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNychaContractsAllDetailsCount('B');
	assertFieldContainsText("Contracts  Data feeds Default Transaction Count", contractsPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Payroll Advanced search Title", contractsPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Payroll Transactions");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

