package io.reisys.checkbook.nycha.budget;


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
	
	@Given("^I navigate to OGE Page$")
	public void navigateTopayrollPage() {
		homePage.open();
				budgetPage.navigateToOGEPage();		       	
	}
	
	@When("^I select Nycha from  OGE drop down$")
	public void navigateToNYCHAPage()  {
		budgetPage.navigateToNYCHAPage();
			
	}
	@When("^I select Budget from top navigation$")
	public void navigateToNYCHABudgetPage()  {
		budgetPage.navigateToBudgetPage();
			
	}		
	
	@Then("^the System displays Nycha Budget  Widget and Visualization titles for \"([^\"]*)\"$")
	public void validateBudgetInformation(String yearSelected) throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", budgetPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Budget Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", budgetPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Budget Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Nycha Budget  Widget counts and Top nav amount for \"([^\"]*)\"$")
	public void validateTotalBudgetWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		//Verify Top nav amount		
		String BudgetAmountFromDB = DatabaseUtil.getNYCHABudgetAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		assertFieldContainsText("Top Navigation Budget Amount", budgetPage.getBudgetAmount(), BudgetAmountFromDB);

		//Verify Top 5 Expense categories
		
		Integer ExpenseCategorieswidgetCountDB = DatabaseUtil.getNYCHABudgetExpenseCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", budgetPage.getWidgetTotalCount("Expense Categories") , ExpenseCategorieswidgetCountDB.toString());
		
		//Verify Top 5 Top 5 Expense categories by committed Expense
		Integer ExpenseCategoriesCEwidgetCountDB = DatabaseUtil.getNYCHABudgetExpenseCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", budgetPage.getWidgetTotalCount2("Expense Categories by Committed Expense Budget") ,  ExpenseCategoriesCEwidgetCountDB.toString());
		//Verify Top 5 Expense categories
		//assertFieldContainsText("Number of Expense Categories", budgetPage.getWidgetTotalCount3("Expense Categories by Percent Difference") , ExpenseCategorieswidgetCountDB.toString());
		
	
		//Verify Top 5 Programs
		Integer ProgramswidgetCountDB = DatabaseUtil.getNYCHABudgetProgramsCount(year, 'B');
		assertFieldContainsText("Number of Programs", budgetPage.getWidgetTotalCount("Programs") , ProgramswidgetCountDB.toString());
		//Verify Top 5 Programs by committed Expense
		Integer ProgramsCEwidgetCountDB = DatabaseUtil.getNYCHABudgetProgramsCount(year, 'B');
		assertFieldContainsText("Number of Programs", budgetPage.getWidgetTotalCount11("Programs by Committed Expense Budget") , ProgramsCEwidgetCountDB.toString());
		//Verify Top 5 Programs by Percent Difference
		//assertFieldContainsText("Number of Programs", budgetPage.getWidgetTotalCount12("Programs by Percent Difference") , ProgramswidgetCountDB.toString());
				
		
		//Verify Top 5 Funding sources
		
		Integer FundingSourcewidgetCountDB = DatabaseUtil.getNYCHABudgetFundingSourceCount(year, 'B');
		assertFieldContainsText("Number of Funding sources", budgetPage.getWidgetTotalCount("Funding Sources") , FundingSourcewidgetCountDB.toString());
						
		//Verify Top 5 Funding sources Centers by committed Expense
		Integer FundingSourceCEwidgetCountDB= DatabaseUtil.getNYCHABudgetFundingSourceCount(year, 'B');
		assertFieldContainsText("Number of Funding sources", budgetPage.getWidgetTotalCount8("Funding Sources by Committed Expense Budget") ,  FundingSourceCEwidgetCountDB.toString());
											
		//Verify Top 5 Funding sources by Percent Difference
		
		//assertFieldContainsText("Number of Funding sources", budgetPage.getWidgetTotalCount9("Funding Sources by Percent Difference") , FundingSourcewidgetCountDB.toString());
					
		
		//Verify Top 5 Responsibility Centers
				
		Integer ResponsibilityCenterswidgetCountDB = DatabaseUtil.getNYCHABudgetRespCentersCount(year, 'B');
		assertFieldContainsText("Number of Responsibility Centers", budgetPage.getWidgetTotalCount("Responsibility Centers") , ResponsibilityCenterswidgetCountDB.toString());
				
		//Verify Top 5 Responsibility Centers by committed Expense
		Integer ResponsibilityCentersCEwidgetCountDB= DatabaseUtil.getNYCHABudgetRespCentersCount(year, 'B');
		assertFieldContainsText("Number of Responsibility Centers", budgetPage.getWidgetTotalCount5("Responsibility Centers by Committed Expense Budget") ,  ResponsibilityCentersCEwidgetCountDB.toString());
		
		//Verify Top 5 Responsibility Centers by Percent Difference
		//assertFieldContainsText("Number of Responsibility Centers", budgetPage.getWidgetTotalCount6("Responsibility Centers by Percent Difference") , ResponsibilityCenterswidgetCountDB.toString());
		
		//Verify Top 5 Projects 
		Integer ProjectswidgetCountDB = DatabaseUtil.getNYCHABudgetProjectsCount(year, 'B');
		assertFieldContainsText("Number of Projects", budgetPage.getWidgetTotalCount("Projects") , ProjectswidgetCountDB.toString());
			
		//Verify Top 5 Projects by committed Expense
		Integer ProjectsCEwidgetCountDB = DatabaseUtil.getNYCHABudgetProjectsCount(year, 'B');
		assertFieldContainsText("Number of Projects", budgetPage.getWidgetTotalCount14("Projects by Committed Expense Budget") , ProjectsCEwidgetCountDB.toString());
		//Verify Top 5 Projects by Percent Difference
		//assertFieldContainsText("Number of Projects", budgetPage.getWidgetTotalCount15("Projects by Percent Difference") , ProjectswidgetCountDB.toString());
		
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Nycha Budget  Widgets Details Information for \"([^\"]*)\"$")
	public void validateBudgetWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		
		budgetPage.navigateToWidgetDetails("Programs");
		Integer NYCHAProgramsWidgetDetailsCountFromDB = DatabaseUtil.getNYCHABudgetDetailsCount(year,'B');
		assertFieldContainsText("Budget Widget Details Transaction Count", budgetPage.getTotalCountForWidgetDetails() , NYCHAProgramsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Budget programs Widget Details Title", budgetPage.getWidgetDetailTitle(), "Programs Expense Budget Transactions");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		
		
		
		
		
		softAssertion.assertAll();
	}


}
	

