package io.reisys.checkbook.citywide.datafeeds.budget;

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

public class DFBudgetSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	DFBudgetPage BudgetPage;
	
	@Given("^I navigate to Data Feeds Page$")
	public void navigateToDatafeedsPage() {
		    homePage.open();
				BudgetPage.navigateToDatafeedsPage();
					
	}
	
	@When("^I navigate to Data Feeds Budget$")
	public void navigateToDatafeedsBudgetPage() {
		BudgetPage.navigateToDatafeedsBudgetPage();
			
	}
	
	
	@Then("^I navigate to Citywide Budget$")
	public void navigateToDatafeedscitywideBudgetPage(){
		BudgetPage.navigateToDatafeedscitywideBudgetPage();	
	}
	

	@And("^I submit the form$")
	public void submitToDatafeedsCitywideBudgetPage(){
		BudgetPage.navigateToDatafeedsCitywideBudgetSubmit1();	
	}


	@And("^the System displays Citywide Budget Transactions$")
    public void the_System_displays_citywide_Budget_Transactions() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	Integer dataFeedsDetailsCountFromDB = DatabaseUtil.getBudgetDetailsCount(2018,'B');
	assertFieldContainsText("Budget  Data feeds Default Transaction Count", BudgetPage.getTotalCountForDatfeeds() ,dataFeedsDetailsCountFromDB.toString());
	//assertFieldHasText("Budget Advanced search Title", BudgetPage.getAdvancedSearchOGEPayrollDetailsTitle(), "NYCHA Budget Transactions");
//	assertFieldContainsText("Budget  Advanced Search Default Transactions Top navigation Amount", BudgetPage.getTransactionAmount() , BudgetAmount);
	
	
	softAssertion.assertAll();
}
	
	
	
	@When("^I enter adopted field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_adopted_field_range_from_to(String from, String to)  {
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
	    // Write code here that turns the phrase above into concrete actions
		BudgetPage.enterRangeValueForAdoptedField(from,to);
	}
	@When("^I enter modified field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_modified_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		BudgetPage.enterRangeValueForModifiedField(from,to);
	}


	@When("^I enter Pre Encumbered field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_recognized_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		BudgetPage.enterRangeValueForPreEncumberedField(from,to);
	}
	@When("^I enter Encumbered field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_pre_Encumbered_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		BudgetPage.enterRangeValueForEncumberedField(from,to);
	}	
	@When("^I enter Accrued Expense field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Accrued_Expense_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		BudgetPage.enterRangeValueForAccruedExpenseField(from,to);
	}
	@When("^I enter Cash Payments field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Cash_Payments_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		BudgetPage.enterRangeValueForCashPaymentsField(from,to);
	}
	@When("^I enter Post Adjustments field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Post_Adjustments_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		BudgetPage.enterRangeValueForPostAdjustmentsField(from,to);
	}


	
	//-----------------------Advanced search  Steps -------------------------------------
	@When("click on Budget Fiscal Year drop down for \"([^\"]*)\"$")
	public void clickOnDropdown(String yearSelected)throws Exception{
		String year = ExecutionContext.getJsonData().get(yearSelected).getAsString();
	    BudgetPage.clickOnBudgetFyDropdown(year);
	}

	@When("click on Agency dropdown and select \"([^\"]*)\"$")
	public void clickOnAgencyDropdown(String value)throws Exception{
		value = ExecutionContext.getJsonData().get(value).getAsString();
	    BudgetPage.clickOnAgencysDropdown(value);
	}
	@When("click on Department dropdown and select \"([^\"]*)\"$")
	public void clickOnDepartmentDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    BudgetPage.clickOnDepartmentDropdown(value);
	}
	@When("click on Expense Category dropdown and select \"([^\"]*)\"$")
	public void clickOnExpenseCategoryDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    BudgetPage.clickOnExpenseCategoryDropdown(value);
	}
	
	
	@When("enter \"([^\"]*)\" as value to field budget code")
	public void passTexttoBudgetCode(String value)
	
	{
		value = ExecutionContext.getJsonData().get(value).getAsString();
		BudgetPage.sendValueToBudgetCode(value);
	}

	/*
	@Then("^I click on Submit button for budget domain$")
	public void navigateToBudgetresultsPage()  {
		BudgetPage.navigateToAdvancedSearchSubmit();
			
	}
	@Then("^the System displays Budget Transactions for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
		Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getBudgetDetailsCount(2019,'B');
		assertFieldContainsText("Budget  Advanced Search Default Transaction Count", BudgetPage.getTotalCountForAdvancedSearchDetails() , advancedSearchDetailsCountFromDB.toString());
		assertFieldHasText("Budget Advanced search Title", BudgetPage.getAdvancedSearchDetailsTitle(), "Budget Transactions");
		
		
		softAssertion.assertAll();
	}*/
	@Then("^we should see \"([^\"]*)\" in the form$")
	public void validateLabel(String Labelname) {
	  List<String> LabelsToVerify = CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get(Labelname).getAsJsonArray());
		for(String name:LabelsToVerify) {
	    String elementXpath= ExecutionContext.getJsonData().get(name).getAsString();
		String result = BudgetPage.getLabelName(elementXpath);
		assertEquals( name,result);
		}
	}
	@Then("^\"([^\"]*)\" dropdown has \"([^\"]*)\" as default value$")
	public void Verifydefaultvalue(String labelName, String value) {
		 String element= ExecutionContext.getJsonData().get(labelName).getAsString();
		String result = BudgetPage.getDropDownDefaultValue(element);
		assertEquals( value,result);
		 
	    
	  
	}


	
	
}
	

