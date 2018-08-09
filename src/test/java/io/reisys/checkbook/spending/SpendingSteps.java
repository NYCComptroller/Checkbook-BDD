package io.reisys.checkbook.spending;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;
import io.reisys.checkbook.home.NYCheckbookPage;

public class SpendingSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	SpendingPage spendingPage;
	
	@Given("^I navigate to Spending Page$")
	public void navigateToSpendingPage() {
		homePage.open();
		spendingPage.navigateToSpendingPage();
	}
	
	@And("I navigate to \"([^\"]*)\" Spending sub tab")
	public void navigateToSubTab(String subTabType) {
		spendingPage.navigateToSubTabType(subTabType);
		assertFieldContainsText("Selected Sub Tab", spendingPage.getCurrentSelectedSubTabType(), subTabType);
	}
	
	//----------------------- Total Spending Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Total Spending Information for \"([^\"]*)\"$")
	public void validateSpendingInformation(String yearSelected) throws Exception {
		String spendingAmountFromDB = DatabaseUtil.getSpendingAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		assertFieldContainsText("Top Navigation Spending Amount", spendingPage.getSpendingAmount(), spendingAmountFromDB);
		assertFieldContainsText("Bottom Navigation Total Spending Amount", spendingPage.getBottomNavSpendingAmount(), spendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Total Spending Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Total Spending Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Total Spending Widgets for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer totalCheckswidgetCountDB = DatabaseUtil.getTotalSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , totalCheckswidgetCountDB.toString());
		
		//Verify Total Agencies
		Integer totalAgenciesWidgetCountDB = DatabaseUtil.getTotalSpendingAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", spendingPage.getWidgetTotalCount("Agencies") , totalAgenciesWidgetCountDB.toString());
		
		//Verify Expense Categories
		Integer totalExpenseCategoriesWidgetCountDB = DatabaseUtil.getTotalSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , totalExpenseCategoriesWidgetCountDB.toString());
		
		//Verify Prime Vendors
		Integer totalPrimeVendorsWidgetCountDB = DatabaseUtil.getTotalSpendingPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", spendingPage.getWidgetTotalCount("Prime Vendors") , totalPrimeVendorsWidgetCountDB.toString());
		
		//Verify Contracts
		Integer totalContractsWidgetCountDB = DatabaseUtil.getTotalSpendingContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", spendingPage.getWidgetTotalCount("Contracts") , totalContractsWidgetCountDB.toString());
	
		softAssertion.assertAll();
	}

	
	//----------------------- Payroll Spending Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Payroll Spending Information for \"([^\"]*)\"$")
	public void validatePayrollSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String payrollSpendingAmountFromDB = DatabaseUtil.getPayrollSpendingAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Payroll Spending Amount", spendingPage.getBottomNavSpendingAmount(), payrollSpendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Spending Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Spending Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Payroll Spending Widgets for \"([^\"]*)\"$")
	public void validatePayrollSpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Agencies
		Integer payrollSpendingAgenciesWidgetCountFromDB = DatabaseUtil.getPayrollAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", spendingPage.getWidgetTotalCount("Agencies") , payrollSpendingAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer payrollSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getPayrollSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , payrollSpendingExpenseCategoriesWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	//----------------------- Capital Spending Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Capital Spending Information for \"([^\"]*)\"$")
	public void validateCaptialSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String capitalSpendingAmountFromDB = DatabaseUtil.getCapitalSpendingAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Capital Spending Amount", spendingPage.getBottomNavSpendingAmount(), capitalSpendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Capital Spending Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Capital Spending Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Capital Spending Widgets for \"([^\"]*)\"$")
	public void validateCapitalSpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer capitalSpendingChecksWidgetCountFromDB = DatabaseUtil.getCapitalSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , capitalSpendingChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer capitalSpendingAgenciesWidgetCountFromDB = DatabaseUtil.getCapitalSpendingAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", spendingPage.getWidgetTotalCount("Agencies") , capitalSpendingAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer capitalSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getCapitalSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , capitalSpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer capitalSpendingPrimeVendorsWidgetCountFromDB = DatabaseUtil.getCapitalSpendingPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", spendingPage.getWidgetTotalCount("Prime Vendors") , capitalSpendingPrimeVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer capitalSpendingContractsWidgetCountFromDB = DatabaseUtil.getCapitalSpendingContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", spendingPage.getWidgetTotalCount("Contracts") , capitalSpendingContractsWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	//----------------------- Contract Spending Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Contract Spending Information for \"([^\"]*)\"$")
	public void validateContractSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String contractSpendingAmountFromDB = DatabaseUtil.getContractSpendingAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Contract Spending Amount", spendingPage.getBottomNavSpendingAmount(), contractSpendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Contract Spending Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Contract Spending Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Contract Spending Widgets for \"([^\"]*)\"$")
	public void validateContractSpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer contractSpendingChecksWidgetCountFromDB = DatabaseUtil.getContractSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , contractSpendingChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer contractSpendingAgenciesWidgetCountFromDB = DatabaseUtil.getContractSpendingAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", spendingPage.getWidgetTotalCount("Agencies") , contractSpendingAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer contractSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getContractSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , contractSpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer contractSpendingPrimeVendorsWidgetCountFromDB = DatabaseUtil.getContractSpendingPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", spendingPage.getWidgetTotalCount("Prime Vendors") , contractSpendingPrimeVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer contractSpendingContractsWidgetCountFromDB = DatabaseUtil.getContractSpendingContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", spendingPage.getWidgetTotalCount("Contracts") , contractSpendingContractsWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	//----------------------- Trust & Agency Spending Sub Tab Steps -------------------------------------

	@Then("^the System displays Trust and Agency Spending Information for \"([^\"]*)\"$")
	public void validateTrustAndAgencySpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String otherSpendingAmountFromDB = DatabaseUtil.getTrustAgencySpendingAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Trust and Agency Spending Amount", spendingPage.getBottomNavSpendingAmount(), otherSpendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Trust and Agency Spending Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Trust and Agency Spending Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	
	@Then("^the System displays Trust and Agency Spending Widgets for \"([^\"]*)\"$")
	public void validateTrustAndAgencySpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer trustAndAgencySpendingChecksWidgetCountFromDB = DatabaseUtil.getTrustAgencySpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , trustAndAgencySpendingChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer trustAndAgencySpendingAgenciesWidgetCountFromDB = DatabaseUtil.getTrustAgencySpendingAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", spendingPage.getWidgetTotalCount("Agencies") , trustAndAgencySpendingAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer trustAndAgencySpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getTrustAgencySpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , trustAndAgencySpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer trustAndAgencySpendingPrimeVendorsWidgetCountFromDB = DatabaseUtil.getTrustAgencySpendingPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", spendingPage.getWidgetTotalCount("Prime Vendors") , trustAndAgencySpendingPrimeVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer trustAndAgencySpendingContractsWidgetCountFromDB = DatabaseUtil.getTrustAgencySpendingContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", spendingPage.getWidgetTotalCount("Contracts") , trustAndAgencySpendingContractsWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	//---------------  Other Spending Sub Tab steps ----------------------------------
	
	@Then("^the System displays Other Spending Information for \"([^\"]*)\"$")
	public void validateOtherSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String otherSpendingAmountFromDB = DatabaseUtil.getOtherSpendingAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Other Spending Amount", spendingPage.getBottomNavSpendingAmount(), otherSpendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Other Spending Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Other Spending Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Other Spending Widgets for \"([^\"]*)\"$")
	public void validateOtherSpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer otherSpendingChecksWidgetCountFromDB = DatabaseUtil.getOtherSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , otherSpendingChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer otherSpendingAgenciesWidgetCountFromDB = DatabaseUtil.getOtherSpendingAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", spendingPage.getWidgetTotalCount("Agencies") , otherSpendingAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer otherSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getOtherSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , otherSpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer otherSpendingPrimeVendorsWidgetCountFromDB = DatabaseUtil.getOtherSpendingPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", spendingPage.getWidgetTotalCount("Prime Vendors") , otherSpendingPrimeVendorsWidgetCountFromDB.toString());

		softAssertion.assertAll();
	}

}
