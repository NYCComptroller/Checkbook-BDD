package io.reisys.checkbook.nycha.advsearch.payroll;

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
	
	@Given("^I navigate to Advanced Search Payroll OGE Page$")
	public void navigateToAdvancedSearchPayrollFormPage() {
		    homePage.open();
				payrollPage.navigateToOGEPage();
				payrollPage.navigateToNYCHAPage();
				payrollPage.navigateToPayrollPage();
				payrollPage.navigateToAdvancedSearchfromPayrollPage();		
			
	}
	
	@When("^I click on Submit$")
	public void navigateToNYCHAPayrollAdvancedSearchResultsPage()  {
		payrollPage.navigateToAdvancedSearchOGEPayrollSubmit();
			
	}
	
	
	/*@And("I navigate to \"([^\"]*)\" Spending sub tab")
	public void navigateToSubTab(String subTabType) {
		payrollPage.navigateToSubTabType(subTabType);
		assertFieldContainsText("Selected Sub Tab", payrollPage.getCurrentSelectedSubTabType(), subTabType);
	}*/
	
	//----------------------- Total Spending Sub Tab Steps -------------------------------------
	
	//@Then("^the System displays Nycha Payroll Transactions and Top navigation amount for \"([^\"]*)\"$")
	/*	@Then("^Then the System displays Nycha Payroll Transactions")
	public void validatePayrollInformation() throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
				
		Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getPayrollDetailsCount(2019,'C');
		assertFieldContainsText("Payroll  Advanced Search Default Transaction Count", payrollPage.getTotalCountForAdvancedSearchOGEPayrollDetails() , advancedSearchDetailsCountFromDB.toString());
		assertFieldHasText("Payroll Advanced search Title", payrollPage.getAdvancedSearchOGEPayrollDetailsTitle(), "Payroll Transactions");
	//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		softAssertion.assertAll();
	}
		*/

@Then("^the System displays Nycha Payroll Transactions$")
public void the_System_displays_Nycha_Payroll_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getNYCHAPayrollDetailsCount(2020,'C');
	assertFieldContainsText("Payroll  Advanced Search Default Transaction Count", payrollPage.getTotalCountForAdvancedSearchOGEPayrollDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Payroll Advanced search Title", payrollPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Payroll Transactions");
//	assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

