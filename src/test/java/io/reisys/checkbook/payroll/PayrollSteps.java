package io.reisys.checkbook.payroll;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class PayrollSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	PayrollPage payrollPage;
	
	@Given("^I navigate to Payroll Advanced search form Page$")
	public void navigateToAdvancedSearchPage() {
		        homePage.open();
		        payrollPage.navigateToPayrollPage();
		       // payrollPage.navigateToAdvancedSearchPage();		  	
	}
	
	
	@When("^I click on Submit$")
	public void navigateToAdvancedSearchResultsPage()  {
		payrollPage.navigateToAdvancedSearchPage();
			
	}
	
	@Then("^the System displays Nycha Payroll Transactions$")
	public void the_System_displays_Nycha_Payroll_Transactions() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getPayrollDetailsCount(2019,'C');
		assertFieldContainsText("Payroll  Advanced Search Default Transaction Count", payrollPage.getTotalCountForAdvancedSearchPayrollDetails() , advancedSearchDetailsCountFromDB.toString());
		assertFieldHasText("Payroll Advanced search Title", payrollPage.getAdvancedSearchOGEPayrollDetailsTitle(), "Payroll Transactions");
//		assertFieldContainsText("Payroll  Advanced Search Default Transactions Top navigation Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		softAssertion.assertAll();
	}

	/*
	 @When("^I enter adopted field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	
	public void i_enter_adopted_field_range_from_to(String from, String to)  {
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
	    // Write code here that turns the phrase above into concrete actions
		revenuePage.enterRangeValueForAdoptedField(from,to);
	}
	@When("^I enter modified field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_modified_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		revenuePage.enterRangeValueForModifiedField(from,to);
	}


	@When("^I enter recognized field range from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_enter_recognized_field_range_from_to(String from, String to) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		from = ExecutionContext.getJsonData().get(from).getAsString();
		to = ExecutionContext.getJsonData().get(to).getAsString();
		revenuePage.enterRangeValueForRecognizedField(from,to);
	}


	
	//-----------------------Advanced search  Steps -------------------------------------
	@Then("click on revenue fy drop down for \"([^\"]*)\"$")
	public void clickOnDropdown(String yearSelected)throws Exception{
		String year = ExecutionContext.getJsonData().get(yearSelected).getAsString();
	    revenuePage.clickOnBudgetFyDropdown(year);
	}
	@Then("click on funding class dropdown for value \"([^\"]*)\"$")
	public void clickOnFundingClassDropdown(String value)throws Exception{
		 value = ExecutionContext.getJsonData().get(value).getAsString();
	    revenuePage.clickOnFundingClassDropdown(value);
	}
	@Then("click on agency dropdown and select agency \"([^\"]*)\"$")
	public void clickOnAgencyDropdown(String value)throws Exception{
		value = ExecutionContext.getJsonData().get(value).getAsString();
	    revenuePage.clickOnAgencysDropdown(value);
	}
	@Then("enter \"([^\"]*)\" as value to field revenue source")
	public void passTexttoRevenueSource(String value)
	
	{
		value = ExecutionContext.getJsonData().get(value).getAsString();
		revenuePage.sendValueToRevenueSource(value);
	}
	@Then("enter \"([^\"]*)\" as value to field revenue class")
	public void passTexttoRevenueClass(String value)
	{
		value = ExecutionContext.getJsonData().get(value).getAsString();
		revenuePage.sendValueToRevenueClass(value);
	}
	
	@Then("^I click on Submit button for revenue domain$")
	public void navigateToRevenueresultsPage()  {
		revenuePage.navigateToAdvancedSearchSubmit();
			
	}
	@Then("^the System displays Revenue Transactions for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
		Integer advancedSearchDetailsCountFromDB = DatabaseUtil.getRevenueDetailsCount(2019,'B');
		assertFieldContainsText("Revenue  Advanced Search Default Transaction Count", revenuePage.getTotalCountForAdvancedSearchPayrollDetails() , advancedSearchDetailsCountFromDB.toString());
		assertFieldHasText("Revenue Advanced search Title", revenuePage.getAdvancedSearchOGEPayrollDetailsTitle(), "Revenue Transactions");
		
		
		softAssertion.assertAll();
	}
	@Then("^we should see \"([^\"]*)\" in the form$")
	public void validateLabel(String Labelname) {
	  List<String> LabelsToVerify = CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get(Labelname).getAsJsonArray());
		for(String name:LabelsToVerify) {
	    String elementXpath= ExecutionContext.getJsonData().get(name).getAsString();
		String result = revenuePage.getLabelName(elementXpath);
		assertEquals( name,result);
		}
	}
	@Then("^\"([^\"]*)\" dropdown has \"([^\"]*)\" as default value$")
	public void Verifydefaultvalue(String labelName, String value) {
		 String element= ExecutionContext.getJsonData().get(labelName).getAsString();
		String result = revenuePage.getDropDownDefaultValue(element);
		assertEquals( value,result);
		 
	  
	  
	}

	 */ 
	
	


}
	

