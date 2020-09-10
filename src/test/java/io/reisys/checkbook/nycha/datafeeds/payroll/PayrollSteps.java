package io.reisys.checkbook.nycha.datafeeds.payroll;

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

public class PayrollSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	PayrollPage payrollPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				payrollPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Datfeeds Payroll$")
	public void navigateToDatafeedsPayrollPage() {
		payrollPage.navigateToDatafeedsPayrollPage();
			
	}
	
	
	@Then("^I navigate to NYCHA payroll$")
	public void navigateToDatafeedsNychaPayrollPage(){
		payrollPage.navigateToDatafeedsNychaPayrollPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsNychaPayrollPage(){
		payrollPage.navigateToDatafeedsNychaPayrollSubmit1();	
	}
	


	@And("^the System displays Nycha Payroll Transactions$")
    public void the_System_displays_Nycha_Payroll_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getNYCHAPayrollDetailsCount(2020,'C');
	assertFieldContainsText("Payroll  Data feeds Default Transaction Count", payrollPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Payroll Advanced search Title", payrollPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Payroll Transactions");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	
	softAssertion.assertAll();
}
	
	
}
	

