package io.reisys.checkbook.budget;


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
	
	@Given("^I navigate to Advanced search form Page$")
	public void navigateToAdvancedSearchPage() {
		homePage.open();
				budgetPage.navigateToOGEPage();		       	
	}
	
	@When("^I select Budget from  Advanced search$")
	public void navigateToBudgetPage()  {
		budgetPage.navigateToNYCHAPage();
			
	}
	
	@When("^I click on Submit$")
	public void navigateToBudgetresultsPage()  {
		budgetPage.navigateToNYCHAPage();
			
	}

	
	//-----------------------Advanced search  Steps -------------------------------------
	
	@Then("^the System displays Budget Transactions for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		
		
		softAssertion.assertAll();
	}
	
	
	@And("^And the System displays  Budget Top nav amount for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		//payrollPage.navigateToWidgetDetails("AnnualSalaries");
		
		
	//	assertFieldContainsText("Payroll  Widget Detail Transaction Count", budgetPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Payroll Checks Widget Details Title", budgetPage.getWidgetDetailTitle(), "Payroll Summary by Agency Title");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
	//("Total Spending Agencies Widget Detail Transaction Amount", budgetPage.getTransactionAmount() , PayrollAmount);

		
		
		softAssertion.assertAll();
	}

}
	

