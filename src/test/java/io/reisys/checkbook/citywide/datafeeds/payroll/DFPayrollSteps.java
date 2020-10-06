package io.reisys.checkbook.citywide.datafeeds.payroll;

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

public class DFPayrollSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFPayrollPage PayrollPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				PayrollPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Data Feeds Payroll$")
	public void navigateToDatafeedsPayrollPage() {
		PayrollPage.navigateToDatafeedsPayrollPage();
			
	}
	
	
	@Then("^I navigate to Citywide Payroll$")
	public void navigateToDatafeedscitywidePayrollPage(){
		PayrollPage.navigateToDatafeedscitywidePayrollPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsCitywidePayrollPage(){
		PayrollPage.navigateToDatafeedsCitywidePayrollSubmit1();	
	}
	


	@And("^the System displays Citywide Payroll Transactions$")
    public void the_System_displays_citywide_Payroll_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getPayrollDetailsCount(2020,'C');
	assertFieldContainsText("Payroll  Data feeds Default Transaction Count", PayrollPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Payroll Advanced search Title", PayrollPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Payroll Transactions");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", PayrollPage.getTransactionAmount() , PayrollAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

