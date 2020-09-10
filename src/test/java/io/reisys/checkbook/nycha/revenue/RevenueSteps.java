package io.reisys.checkbook.nycha.revenue;


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
	
	@Given("^I navigate to OGE Page$")
	public void navigateTopayrollPage() {
		homePage.open();
				RevenuePage.navigateToOGEPage();		       	
	}
	
	@When("^I select Nycha from  OGE drop down$")
	public void navigateToNYCHAPage()  {
		RevenuePage.navigateToNYCHAPage();
		homePage.selectYear("2018");
			
	}
   /* @When("^I select \"([^\"]*)\" for getting data$")
	
	public void selectYear(String year) {
		homePage.selectYear(year);
	}*/
	@When("^I select Revenue from top navigation$")
	public void navigateToNYCHARevenuePage()  {
		RevenuePage.navigateToRevenuePage();
			
	}		
	
	@Then("^the System displays Nycha Revenue  Widget and Visualization titles for \"([^\"]*)\"$")
	public void validateRevenueInformation(String yearSelected) throws Exception {
	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", RevenuePage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Revenue Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", RevenuePage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Revenue Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Nycha Revenue  Widget counts and Top nav amount for \"([^\"]*)\"$")
	public void validateTotalRevenueWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Top nav amount		
		String RevenueAmountFromDB = DatabaseUtil.getNYCHARevenueAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		assertFieldContainsText("Top Navigation Revenue Amount", RevenuePage.getRevenueAmount(), RevenueAmountFromDB);

		//Verify Top 5 Expense categories
		
		Integer ExpenseCategorieswidgetCountDB = DatabaseUtil.getNYCHARevenueExpenseCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", RevenuePage.getWidgetTotalCount("Expense Categories") , ExpenseCategorieswidgetCountDB.toString());
		
		
		
		//Verify Top 5 Programs
		Integer ProgramswidgetCountDB = DatabaseUtil.getNYCHARevenueProgramsCount(year, 'B');
		assertFieldContainsText("Number of Programs", RevenuePage.getWidgetTotalCount("Programs") , ProgramswidgetCountDB.toString());
		//Verify Top 5 Programs by committed Expense
			
		
		//Verify Top 5 Funding sources
		
		Integer FundingSourcewidgetCountDB = DatabaseUtil.getNYCHARevenueFundingSourceCount(year, 'B');
		assertFieldContainsText("Number of Funding sources", RevenuePage.getWidgetTotalCount("Funding Sources") , FundingSourcewidgetCountDB.toString());
						
				
		
		//Verify Top 5 Responsibility Centers
				
		Integer ResponsibilityCenterswidgetCountDB = DatabaseUtil.getNYCHARevenueRespCentersCount(year, 'B');
		assertFieldContainsText("Number of Responsibility Centers", RevenuePage.getWidgetTotalCount("Responsibility Centers") , ResponsibilityCenterswidgetCountDB.toString());
				
	
		//Verify Top 5 Projects 
		Integer ProjectswidgetCountDB = DatabaseUtil.getNYCHARevenueProjectsCount(year, 'B');
		assertFieldContainsText("Number of Projects", RevenuePage.getWidgetTotalCount("Projects") , ProjectswidgetCountDB.toString());
			
		
		softAssertion.assertAll();
	}
	/*
	@And("^the System displays  Nycha Revenue  Widgets Details Information for \"([^\"]*)\"$")
	public void validateRevenueWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		
		RevenuePage.navigateToWidgetDetails("Programs");
		Integer NYCHAProgramsWidgetDetailsCountFromDB = DatabaseUtil.getNYCHARevenueDetailsCount(year,'B');
		assertFieldContainsText("Revenue Widget Details Transaction Count", RevenuePage.getTotalCountForWidgetDetails() , NYCHAProgramsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Revenue programs Widget Details Title", RevenuePage.getWidgetDetailTitle(), "Programs Revenue Transactions");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		*/
	
	@And("^the System displays  Nycha Revenue  Widgets Details Information for \"([^\"]*)\"$")
	public void validateRevenueWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		
		RevenuePage.navigateToWidgetDetails("Expense Categories");
		Integer NYCHAProgramsWidgetDetailsCountFromDB = DatabaseUtil.getNYCHARevenueDetailsCount(year,'B');
		assertFieldContainsText("Revenue Widget Details Transaction Count", RevenuePage.getTotalCountForWidgetDetails() , NYCHAProgramsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Revenue Expense Categories Widget Details Title", RevenuePage.getWidgetDetailTitle(), "Expense Categories Revenue Transactions");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		
		softAssertion.assertAll();
	}


}
	

