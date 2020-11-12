package io.reisys.checkbook.payroll;


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
	
	@Given("^I navigate to payroll Advanced search form Page$")
	public void navigateToAdvancedSearchPage() {
		        homePage.open();
		        payrollPage.navigateToPayrollPage();
		        payrollPage.navigateToAdvancedSearchPage();		  	
	}	
	
	//@When("^I click on Submit$")
	//public void navigateToAdvancedSearchResultsPage()  {
	//	payrollPage.navigateToAdvancedSearchPage();
			
	//}
	//Range values

	 @When("^I enter Base Pay field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	
	public void i_enter_BasePay_field_range_from_to(String from, String to)  {
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
	    // Write code here that turns the phrase above into concrete actions
		payrollPage.enterRangeValueForBasePayField(from,to);
	}
	@When("^I enter Overtime Payments field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Overtime_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		payrollPage.enterRangeValueForOvertimePayField(from,to);
	}


	@When("^I enter Gross Pay YTD field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Gross_Pay_YTD_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		payrollPage.enterRangeValueForGrossPayYTDField(from,to);
	}
	@When("^I enter Gross Pay field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Gross_Pay_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		payrollPage.enterRangeValueForGrossPayField(from,to);
	}

	@When("^I enter Amount field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_amount_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		payrollPage.enterRangeValueForAmountField(from,to);
	}
	@When("^I enter Pay Date field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Pay_date_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		payrollPage.enterRangeValueForPayDateField(from,to);
	}
	@When("^I enter Other Payments field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Other_Payments_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		payrollPage.enterRangeValueForOtherPaymentsField(from,to);
	}

	@Then("enter \"([^\"]*)\" as value to field Title")
	public void passTexttoPayrollSource(String value)
	
	{
		value = ExecutionContext.getJsonData().get(value).getAsString();
		payrollPage.sendValueToTitle(value);
	}


	
	//-----------------------Advanced search  Steps Dropdown.radio-------------------------------------
	@Then("click on Payroll year drop down and select \"([^\"]*)\"$")
	public void clickOnYearDropdown(String yearSelected)throws Exception{
		String year = ExecutionContext.getJsonData().get(yearSelected).getAsString();
	    payrollPage.clickOnYearDropdown(year);
	}
	@Then("click on Pay Frequency drop down and select \"([^\"]*)\"$")
	public void clickOnPaFrequencyDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    payrollPage.clickOnPayFrequencyDropdown(value);
	}
	@Then("click on agency drop down and select \"([^\"]*)\"$")
	public void clickOnAgencyDropdown(String value)throws Exception{
		value = ExecutionContext.getJsonData().get(value).getAsString();
	    payrollPage.clickOnAgencysDropdown(value);
	}
	@Then("click on salaried type radio button and select \"([^\"]*)\"$")
	public void clickOnSalariedTypeDropdown(String value)throws Exception{
		value = ExecutionContext.getJsonData().get(value).getAsString();
	    payrollPage.clickOnSalariedTypeRadiobutton(value);
	}

	
	@Then("^I click on Submit button for payroll domain$")
	public void navigateToPayrollresultsPage()  {
		payrollPage.navigateToAdvancedSearchSubmit();
			
	}
	@Then("^the System displays Payroll Transactions for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
		Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getPayrollDetailsCount(2019,'C');
		assertFieldContainsText("Payroll  Advanced Search Default Transaction Count", payrollPage.getAdvancedSearchTotalCount() , advancedSearchDetailsCountFromDB.toString());
		assertFieldHasText("Payroll Advanced search Title", payrollPage.getAdvancedSearchTitle(), "Payroll Transactions");
		
		
		softAssertion.assertAll();
	}
	@Then("^we should see \"([^\"]*)\" in the form$")
	public void validateLabel(String Labelname) {
	  List<String> LabelsToVerify = CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get(Labelname).getAsJsonArray());
		for(String name:LabelsToVerify) {
	    String elementXpath= ExecutionContext.getJsonData().get(name).getAsString();
		String result = payrollPage.getLabelName(elementXpath);
		assertEquals( name,result);
		}
	}
	@Then("^\"([^\"]*)\" drop down has \"([^\"]*)\" as default value$")
	public void Verifydefaultvalue(String labelName, String value) {
		 String element= ExecutionContext.getJsonData().get(labelName).getAsString();
		String result = payrollPage.getDropDownDefaultValue(element);
		assertEquals( value,result);
		
	}
				 
	@Then("^\"([^\"]*)\" radio button has \"([^\"]*)\" as default value$")
		public void VerifydefaultvalueRa(String labelName, String value) {
		 String element= ExecutionContext.getJsonData().get(labelName).getAsString();
		String result = payrollPage.getRadiobuttonDefaultValue(element);
	assertEquals( value,result);
			 
		 
	  
	  
	}	
	


}
	

