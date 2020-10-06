package io.reisys.checkbook.citywide.revenue;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class RevenueSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	RevenuePage RevenuePage;
	
	@Given("^I navigate to Citywide Revenue Page$")
	public void navigateTopayrollPage() {
		homePage.open();
		RevenuePage.navigateToRevenuePage();				      	
	}	

	 @When("^I select \"([^\"]*)\" for getting data$")
	
	public void selectYear(String year) {
		homePage.selectYear(year);
	}
		
	
	@Then("^the System displays Citywide Revenue  Widget and Visualization titles for \"([^\"]*)\"$")
	public void validateRevenueInformation(String yearSelected) throws Exception {
	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", RevenuePage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Revenue Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", RevenuePage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Revenue Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Citywide Revenue  Widget counts and Top nav amount for \"([^\"]*)\"$")
	public void validateTotalRevenueWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Top nav amount		
		String RevenueAmountFromDB = DatabaseUtil.getRevenueAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		assertFieldContainsText("Top Navigation Revenue Amount", RevenuePage.getRevenueAmount(), RevenueAmountFromDB);

		//Verify Top 5 Agencies
		
		Integer ExpenseCategorieswidgetCountDB = DatabaseUtil.getRevenueAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", RevenuePage.getWidgetTotalCount("Agencies") , ExpenseCategorieswidgetCountDB.toString());
		
		
		
		//Verify Top 5 RevenueCategories
		Integer RevenueCategorieswidgetCountDB = DatabaseUtil.getRevenueCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Revenue Categories", RevenuePage.getWidgetTotalCount("Revenue Categories") , RevenueCategorieswidgetCountDB.toString());
		//Verify Top 5 Programs by committed Expense
			
		
		//Verify Top 5 Revenue By Funding class
		Integer RevenueFundingClasswidgetCountDB = DatabaseUtil.getRevenueFundingclassCount(year, 'B');
		assertFieldContainsText("Number of Revenue Categories", RevenuePage.getWidgetTotalCount("Revenue Categories") , RevenueFundingClasswidgetCountDB.toString());
		//Verify Top 5 Programs by committed Expense
			
		
		softAssertion.assertAll();
	}

	
	@And("^the System displays  Citywide Revenue  Widgets Details Information for \"([^\"]*)\"$")
	public void validateRevenueWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		
		RevenuePage.navigateToWidgetDetails("Agencies");
		Integer CitywideProgramsWidgetDetailsCountFromDB = DatabaseUtil.getRevenueDetailsCount(year,'B');
		assertFieldContainsText("Revenue Widget Details Transaction Count", RevenuePage.getTotalCountForWidgetDetails() , CitywideProgramsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Revenue Agencies Widget Details Title", RevenuePage.getWidgetDetailTitle(), "Agencies Revenue Transactions");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		
		softAssertion.assertAll();
	}


}
	

