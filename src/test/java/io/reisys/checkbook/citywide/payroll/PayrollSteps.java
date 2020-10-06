package io.reisys.checkbook.citywide.payroll;


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
	
	@Given("^I navigate to Citywide Payroll Page$")
	public void navigateTopayrollPage() {
		homePage.open();
		payrollPage.navigateToPayrollPage();				      	
	}	

	
	 @When("^I select \"([^\"]*)\" for getting data$")
		
		public void selectYear(String year) {
			homePage.selectYear(year);
		}
			

	
	
	@Then("^the System displays Citywide Payroll Widget and Visualization titles for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", payrollPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", payrollPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@Then("^the System displays  Citywide Payroll  Widget counts and Top nav amount for \"([^\"]*)\"$")
	public void validateTotalPayrollWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Top 5 Agencies  by Payroll
		
		Integer AgencieswidgetCountDB = DatabaseUtil. getPayrollAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies by Payroll", payrollPage.getWidgetTotalCount("Agencies") , AgencieswidgetCountDB.toString());
		
		//Verify Top 5 Agencies by Overtime
		
		assertFieldContainsText("Number of Agencies by OVertime", payrollPage.getWidgetTotalCount("Agencies") , AgencieswidgetCountDB.toString());
		
	
		//Verify Top 5 Annual Salaries
				Integer AnnualSalarieswidgetCountDB = DatabaseUtil.getPayrollSalCount(year, 'B');
				assertFieldContainsText("Number of Salaried Employees", payrollPage.getWidgetTotalCount("Salaried Employees") , AnnualSalarieswidgetCountDB.toString());
				
				String payrollAmountFromDB = DatabaseUtil.getPayrollAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
				assertFieldContainsText("Top Navigation Payroll Amount", payrollPage.getPayrollAmount(), payrollAmountFromDB);
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Citywide Payroll  Widgets Details Information for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		//payrollPage.navigateToWidgetDetails("AnnualSalaries");
		
		payrollPage.navigateToWidgetDetails("Salaried Employees");
		Integer totalPayrollWidgetCountFromDB = DatabaseUtil.getPayrollDetailsCount(year,'B');
		assertFieldContainsText("Payroll  Widget Detail Transaction Count", payrollPage.getTotalCountForWidgetDetails() , totalPayrollWidgetCountFromDB.toString());
		assertFieldHasText("Payroll Checks Widget Details Title", payrollPage.getWidgetDetailTitle(), "Payroll Summary by Agency Title");
	//	assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		
		
		//Verify Titles  Widget Details
	//	payrollPage.navigateToWidgetDetails("TitlesbyNumberofEmployees");
	//	Integer PayrollWidgetCountFromDB = DatabaseUtil.getPayrollTitleDetailsCount(year,'B');
	//	assertFieldContainsText("Payroll  Widget Widget Detail Transaction Count", payrollPage.getTotalCountForWidgetDetails() , PayrollWidgetCountFromDB.toString());
	//	assertFieldHasText("Payroll  Widget Widget Details Title", payrollPage.getWidgetDetailTitle(), "Payroll Summary by Number of Employees");
		//assertFieldContainsText("Total Spending Agencies Widget Detail Transaction Amount", payrollPage.getTransactionAmount() , PayrollAmount);
		
		
		
		softAssertion.assertAll();
	}

}
	

