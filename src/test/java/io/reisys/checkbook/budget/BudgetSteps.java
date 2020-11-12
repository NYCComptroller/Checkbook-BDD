package io.reisys.checkbook.budget;


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

public class BudgetSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	BudgetPage budgetPage;
	
	@Given("^I navigate to budget Advanced search form Page$")
	public void navigateToAdvancedSearchPage() {
		        homePage.open();
		        budgetPage.navigateToPayrollPage();
		        budgetPage.navigateToAdvancedSearchPage();		  	
	}

	@When("^I enter adopted field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_adopted_field_range_from_to(String from, String to)  {
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
	    // Write code here that turns the phrase above into concrete actions
		budgetPage.enterRangeValueForAdoptedField(from,to);
	}
	@When("^I enter modified field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_modified_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		budgetPage.enterRangeValueForModifiedField(from,to);
	}


	@When("^I enter Pre Encumbered field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_recognized_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		budgetPage.enterRangeValueForPreEncumberedField(from,to);
	}
	@When("^I enter Encumbered field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_pre_Encumbered_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		budgetPage.enterRangeValueForEncumberedField(from,to);
	}

	
	@When("^I enter Accrued Expense field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Accrued_Expense_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		budgetPage.enterRangeValueForAccruedExpenseField(from,to);
	}
	@When("^I enter Cash Payments field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Cash_Payments_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		budgetPage.enterRangeValueForCashPaymentsField(from,to);
	}
	@When("^I enter Post Adjustments field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_Post_Adjustments_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		budgetPage.enterRangeValueForPostAdjustmentsField(from,to);
	}


	
	//-----------------------Advanced search  Steps -------------------------------------
	@When("click on Budget Fiscal Year drop down for \"([^\"]*)\"$")
	public void clickOnDropdown(String yearSelected)throws Exception{
		String year = ExecutionContext.getJsonData().get(yearSelected).getAsString();
	    budgetPage.clickOnBudgetFyDropdown(year);
	}

	@When("click on Agency dropdown and select \"([^\"]*)\"$")
	public void clickOnAgencyDropdown(String value)throws Exception{
		value = ExecutionContext.getJsonData().get(value).getAsString();
	    budgetPage.clickOnAgencysDropdown(value);
	}
	@When("click on Department dropdown and select \"([^\"]*)\"$")
	public void clickOnDepartmentDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    budgetPage.clickOnDepartmentDropdown(value);
	}
	@When("click on Expense Category dropdown and select \"([^\"]*)\"$")
	public void clickOnExpenseCategoryDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    budgetPage.clickOnExpenseCategoryDropdown(value);
	}
	
	@When("click on Budget Code dropdown and select \"([^\"]*)\"$")
	public void clickOnBudgetCodeDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    budgetPage.clickOnBudgetCodeDropdown(value);
	}
	@When("click on Budget Name dropdown and select \"([^\"]*)\"$")
	public void clickOnBudgetNameDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    budgetPage.clickOnBudgetNameDropdown(value);
	}
	@When("enter \"([^\"]*)\" as value to field budget code")
	public void passTexttoBudgetCode(String value)
	
	{
		value = ExecutionContext.getJsonData().get(value).getAsString();
		budgetPage.sendValueToBudgetCode(value);
	}
	@When("enter \"([^\"]*)\" as value to field budget name")
	public void passTexttoBudgetName(String value)
	{
		value = ExecutionContext.getJsonData().get(value).getAsString();
		budgetPage.sendValueToBudgetName(value);
	}
	
	@Then("^I click on Submit button for budget domain$")
	public void navigateToBudgetresultsPage()  {
		budgetPage.navigateToAdvancedSearchSubmit();
			
	}
	@Then("^the System displays Budget Transactions for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
		Integer advancedSearchDetailsCountFromDB = 5;
				//DatabaseUtil.getBudgetDetailsCount(2019,'B');
		assertFieldContainsText("Budget  Advanced Search Default Transaction Count", budgetPage.getTotalCountForAdvancedSearchDetails() , advancedSearchDetailsCountFromDB.toString());
		assertFieldHasText("Budget Advanced search Title", budgetPage.getAdvancedSearchDetailsTitle(), "Budget Transactions");
		
		
		softAssertion.assertAll();
	}
	@Then("^we should see \"([^\"]*)\" in the form$")
	public void validateLabel(String Labelname) {
	  List<String> LabelsToVerify = CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get(Labelname).getAsJsonArray());
		for(String name:LabelsToVerify) {
	    String elementXpath= ExecutionContext.getJsonData().get(name).getAsString();
		String result = budgetPage.getLabelName(elementXpath);
		assertEquals( name,result);
		}
	}
	@Then("^\"([^\"]*)\" dropdown has \"([^\"]*)\" as default value$")
	public void Verifydefaultvalue(String labelName, String value) {
		 String element= ExecutionContext.getJsonData().get(labelName).getAsString();
		String result = budgetPage.getDropDownDefaultValue(element);
		assertEquals( value,result);
		 
	    
	  
	}


}
	

