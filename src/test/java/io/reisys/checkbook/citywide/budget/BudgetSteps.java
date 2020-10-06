package io.reisys.checkbook.citywide.budget;


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
	
	@Given("^I navigate to Citywide Budget Page$")
	public void navigateTopayrollPage() {
		homePage.open();
		budgetPage.navigateToBudgetPage();	       	
	}
	
	   @When("^I select \"([^\"]*)\" for getting data$")
		
		public void selectYear(String year) {
			homePage.selectYear(year);
		}	
	
	@Then("^the System displays Citywide Budget Widgets and Visualizations titles for \"([^\"]*)\"$")
	public void validateBudgetInformation(String yearSelected) throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", budgetPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Budget Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", budgetPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Budget Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays Citywide  Budget Widgets counts and Top nav amount for \"([^\"]*)\"$")
	public void validateTotalBudgetWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		//Verify Top nav amount		
		String BudgetAmountFromDB = DatabaseUtil.getBudgetAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		assertFieldContainsText("Top Navigation Budget Amount", budgetPage.getBudgetAmount(), BudgetAmountFromDB);

		//Verify Top 5 Expense categories
		
		Integer ExpenseCategorieswidgetCountDB = DatabaseUtil.getBudgetExpenseCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", budgetPage.getWidgetTotalCount("Expense Categories") , ExpenseCategorieswidgetCountDB.toString());
		
		//Verify Top 5 Top 5 Expense categories by committed Expense
		Integer ExpenseCategoriesCEwidgetCountDB = DatabaseUtil.getBudgetExpenseCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", budgetPage.getWidgetTotalCount8("Expense Categories by Committed Expense Budget") ,  ExpenseCategoriesCEwidgetCountDB.toString());
		//Verify Top 5 Expense categories by  Percent Difference
		assertFieldContainsText("Number of Expense Categories", budgetPage.getWidgetTotalCount9("Expense Categories by Percent Difference") , ExpenseCategorieswidgetCountDB.toString());
		
	
		//Verify Top 5 Agencies
		Integer AgencieswidgetCountDB = DatabaseUtil.getBudgetAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", budgetPage.getWidgetTotalCount("Agencies") , AgencieswidgetCountDB.toString());
		//Verify Top 5 Agencies by committed Expense
		Integer AgenciesCEwidgetCountDB = DatabaseUtil.getBudgetAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", budgetPage.getWidgetTotalCount2("Agencies by Committed Expense Budget") , AgenciesCEwidgetCountDB.toString());
		//Verify Top 5 Agencies by Percent Difference
		assertFieldContainsText("Number of Agencies", budgetPage.getWidgetTotalCount3("Agencies by Percent Difference") , AgencieswidgetCountDB.toString());
			
		
	
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Citywide Budget  Widgets Details Information for \"([^\"]*)\"$")
	public void validateBudgetWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		
		budgetPage.navigateToWidgetDetails("Agencies");
		Integer AgenciesWidgetDetailsCountFromDB = DatabaseUtil.getBudgetDetailsCount(year,'B');
		assertFieldContainsText("Budget Widget Details Transaction Count", budgetPage.getTotalCountForWidgetDetails() , AgenciesWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Budget programs Widget Details Title", budgetPage.getWidgetDetailTitle(), "Agencies Expense Budget Transactions");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		
		
		
		
		
		softAssertion.assertAll();
	}


}
	

