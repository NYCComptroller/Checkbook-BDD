package io.reisys.checkbook.citywide.advsearchpayroll;

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

public class ADVPayrollSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ADVPayrollPage PayrollPage;
	
	@Given("^I navigate to Advanced Search Citywide Payroll Page$")
	public void navigateToAdvancedSearchPayrollFormPage() {
		    homePage.open();
		    PayrollPage.navigateToPayrollPage();
		    PayrollPage.navigateToAdvancedSearchfromPayrollPage();		
			}
	
	@When("^I click on Submit$")
	public void navigateToCitywidePayrollAdvancedSearchResultsPage()  {
		PayrollPage.navigateToAdvancedSearchPayrollSubmit();
			
	}
		
@Then("^the System displays Citywide Payroll Transactions$")
public void the_System_displays_Citywide_Payroll_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getCitywidePayrollTransactionCount('C');
	assertFieldContainsText("Payroll  Advanced Search Default Transaction Count", PayrollPage.getTotalCountForAdvancedSearchCitywidePayrollDetails() , advancedSearchDetailsCountFromDB.toString());
	assertFieldHasText("Payroll Advanced search Title", PayrollPage.getAdvancedSearchCitywidePayrollDetailsTitle(), "Payroll Transactions");
//	assertFieldContainsText("Spending  Advanced Search Default Transactions Top navigation Amount", spendingPage.getTransactionAmount() , SpendingAmount);
	
	softAssertion.assertAll();
}
	
	
}
	

