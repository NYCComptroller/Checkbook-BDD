package io.reisys.checkbook.nycha.payroll;


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
	
	@Given("^I navigate to OGE Page$")
	public void navigateTopayrollPage() {
		homePage.open();
				payrollPage.navigateToOGEPage();		       	
	}
	
	@When("^I select Nycha from  OGE drop down$")
	public void navigateToNYCHAPage()  {
		payrollPage.navigateToNYCHAPage();
			
	}
	
	
	/*@And("I navigate to \"([^\"]*)\" Spending sub tab")
	public void navigateToSubTab(String subTabType) {
		payrollPage.navigateToSubTabType(subTabType);
		assertFieldContainsText("Selected Sub Tab", payrollPage.getCurrentSelectedSubTabType(), subTabType);
	}*/
	
	//----------------------- Total Spending Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Nycha Payroll  Widget and Visualization titles for \"([^\"]*)\"$")
	public void validatePayrollInformation(String yearSelected) throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", payrollPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", payrollPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@Then("^the System displays  Nycha Payroll  Widget counts and Top nav amount for \"([^\"]*)\"$")
	public void validateTotalPayrollWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		/*
		//Verify Top 5 Annual Salaries
		
		Integer AnnualSalarieswidgetCountDB = DatabaseUtil.getPayrollSalCount(year, 'B');
		assertFieldContainsText("Number of Annual Salaries", payrollPage.getWidgetTotalCount("Annual Salaries") , AnnualSalarieswidgetCountDB.toString());
		
		//Verify Top 5 Titles by Number of Employees
		Integer TitlesbyNumberofEmployeesWidgetCountDB = DatabaseUtil.getPayrollSalCount(year, 'B');
		assertFieldContainsText("Number of Titles by Number of Employees", payrollPage.getWidgetTotalCount("TitlesbyNumberofEmployees") , TitlesbyNumberofEmployeesWidgetCountDB.toString());
		
	*/
		//Verify Top 5 Annual Salaries
				Integer AnnualSalarieswidgetCountDB = DatabaseUtil.getPayrollSalCount(year, 'B');
				assertFieldContainsText("Number of Salaried Employees", payrollPage.getWidgetTotalCount("Salaried Employees") , AnnualSalarieswidgetCountDB.toString());
				
				String payrollAmountFromDB = DatabaseUtil.getPayrollAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
				assertFieldContainsText("Top Navigation Payroll Amount", payrollPage.getPayrollAmount(), payrollAmountFromDB);
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays  Nycha Payroll  Widgets Details Information for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String totalPAyrollAmount =  DatabaseUtil.getPayrollAmount(year,'B');
		
		//Verify Annual Salaries Widget Details
		//payrollPage.navigateToWidgetDetails("AnnualSalaries");
		
		payrollPage.navigateToWidgetDetails("Salaried Employees");
		Integer totalChecksWidgetCountFromDB = DatabaseUtil.getPayrollDetailsCount(year,'B');
		assertFieldContainsText("Payroll  Widget Detail Transaction Count", payrollPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
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
	

