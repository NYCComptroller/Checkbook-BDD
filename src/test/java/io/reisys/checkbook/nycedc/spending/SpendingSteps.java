package io.reisys.checkbook.nycedc.spending;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class SpendingSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	SpendingPage spendingPage;
	
	@Given("^I navigate to Spending Page$")
	public void navigateToSpendingPage() {
		homePage.open();
		spendingPage.navigateToOGEPage();
		spendingPage.navigateToNYCEDCPage() ;
		
	}
	//   @When("^I select \"([^\"]*)\" for getting data$")
		
	//	public void selectYear(String year) {
	//		homePage.selectYear(year);
		//}
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
	
	@And("^the System displays Total Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String totalSpendingAmount =  DatabaseUtil.getTotalSpendingDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		Integer totalChecksWidgetCountFromDB = DatabaseUtil.getTotalSpendingChecksCount(year,'B');
		assertFieldContainsText("Total Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Total Spending Transactions");
		assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");		
		
		//Verify Total Agencies Widget Details
		spendingPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Total Spending Agencies Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Spending Agencies Widget Details Title", spendingPage.getWidgetDetailTitle(), "Agencies Total Spending Transactions");
		assertFieldContainsText("Total Spending Agencies Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Total Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Total Spending Transactions");
		assertFieldContainsText("Total Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Prime Vendors
		spendingPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Total Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Prime Vendors Total Spending Transactions");
		assertFieldContainsText("Total Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Contracts
		spendingPage.navigateToWidgetDetails("Contracts");
		Integer totalContractsWidgetCountFromDB = DatabaseUtil.getTotalSpendingContractsDetailsCount(year,'B');
		assertFieldContainsText("Total Spending Contracts Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalContractsWidgetCountFromDB.toString());
		assertFieldHasText("Total Spending Contracts Widget Details Title", spendingPage.getWidgetDetailTitle(), "Contracts Total Spending Transactions");
		assertFieldContainsText("Total Spending Contracts Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
	
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
	
	@And("^the System displays Payroll Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validatePayrollSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String payrollSpendingAmountFromDB =  DatabaseUtil.getPayrollSpendingDetailsAmount(year,'B');
		Integer payrollSpendingCountFromDB = DatabaseUtil.getPayrollSpendingDetailsCount(year,'B');
		
		//Verify Total Agencies Widget Details
		spendingPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Payroll Spending Agencies Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , payrollSpendingCountFromDB.toString());
		assertFieldHasText("Payroll Spending Agencies Widget Details Title", spendingPage.getWidgetDetailTitle(), "Agencies Payroll Spending Transactions");
		assertFieldContainsText("Payroll Spending Agencies Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , payrollSpendingAmountFromDB);
		spendingPage.navigateToSubTabType("Payroll");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Payroll Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , payrollSpendingCountFromDB.toString());
		assertFieldHasText("Payroll Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Payroll Spending Transactions");
		assertFieldContainsText("Payroll Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , payrollSpendingAmountFromDB);
		

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
	
	@And("^the System displays Capital Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateCapitalSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String capitalSpendingAmount =  DatabaseUtil.getCapitalContractsSpendingDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		Integer capitalSpendingDetailsCountFromDB = DatabaseUtil.getCapitalSpendingDetailsCount(year,'B');
		assertFieldContainsText("Capital Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , capitalSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Capital Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Capital Spending Transactions");
		assertFieldContainsText("Capital Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , capitalSpendingAmount);
		spendingPage.navigateToSubTabType("Capital");
		
		
		//Verify Total Agencies Widget Details
		spendingPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Capital Spending Agencies Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , capitalSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Capital Spending Agencies Widget Details Title", spendingPage.getWidgetDetailTitle(), "Agencies Capital Spending Transactions");
		assertFieldContainsText("Capital Spending Agencies Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , capitalSpendingAmount);
		spendingPage.navigateToSubTabType("Capital");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Capital Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , capitalSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Capital Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Capital Spending Transactions");
		assertFieldContainsText("Capital Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , capitalSpendingAmount);
		spendingPage.navigateToSubTabType("Capital");
		
		//Verify Prime Vendors
		spendingPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Capital Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), capitalSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Capital Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Prime Vendors Capital Spending Transactions");
		assertFieldContainsText("Capital Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , capitalSpendingAmount);
		spendingPage.navigateToSubTabType("Capital");
		
		//Verify Contracts
		spendingPage.navigateToWidgetDetails("Contracts");
		Integer capitalContractsWidgetCountFromDB = DatabaseUtil.getCapitalSpendingContractsDetailsCount(year,'B');
		String capitalContractsWidgetAmountFromDB = DatabaseUtil.getCapitalContractsSpendingContractsDetailsAmount(year, 'B');
		assertFieldContainsText("Capital Spending Contracts Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , capitalContractsWidgetCountFromDB.toString());
		assertFieldHasText("Capital Spending Contracts Widget Details Title", spendingPage.getWidgetDetailTitle(), "Contracts Capital Spending Transactions");
		assertFieldContainsText("Capital Spending Contracts Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , capitalContractsWidgetAmountFromDB);
	
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
	
	@And("^the System displays Contract Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateContractSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String contractSpendingDetailAmount =  DatabaseUtil.getContractsSpendingDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		Integer contractSpendingDetailsCountFromDB = DatabaseUtil.getContractSpendingDetailsCount(year,'B');
		assertFieldContainsText("Contract Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Agencies Widget Details
		spendingPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Contract Spending Agencies Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Agencies Widget Details Title", spendingPage.getWidgetDetailTitle(), "Agencies Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Agencies Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Contract Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors
		spendingPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Contract Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Prime Vendors Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Contracts
		spendingPage.navigateToWidgetDetails("Contracts");
		Integer contractsWidgetCountFromDB = DatabaseUtil.getContractSpendingContractsDetailsCount(year,'B');
		String contractsWidgetAmountFromDB = DatabaseUtil.getContractsSpendingContractsDetailsAmount(year, 'B');
		assertFieldContainsText("Contract Spending Contracts Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractsWidgetCountFromDB.toString());
		assertFieldHasText("Contract Spending Contracts Widget Details Title", spendingPage.getWidgetDetailTitle(), "Contracts Spending Transactions");
		assertFieldContainsText("Contract Spending Contracts Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractsWidgetAmountFromDB);
	
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
	
	@And("^the System displays Trust and Agency Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateTrustAndAgencySpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String spendingDetailAmount =  DatabaseUtil.getTrustAgencySpendingDetailsAmount(year,'B');
		Integer spendingDetailsCountFromDB = DatabaseUtil.getTrustAgencySpendingDetailsCount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		assertFieldContainsText("Trust & Agency Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Trust & Agency Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Agencies Widget Details
		spendingPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Trust & Agency Spending Agencies Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Agencies Widget Details Title", spendingPage.getWidgetDetailTitle(), "Agencies Trust & Agency Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Agencies Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Trust & Agency Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Trust & Agency Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors
		spendingPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Trust & Agency Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Prime Vendors Trust & Agency Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Contracts
		spendingPage.navigateToWidgetDetails("Contracts");
		Integer contractsWidgetCountFromDB = DatabaseUtil.getTrustAgencySpendingContractsDetailsCount(year,'B');
		String contractsWidgetAmountFromDB = DatabaseUtil.getTrustAgencySpendingContractsDetailsAmount(year, 'B');
		assertFieldContainsText("Trust & Agency Spending Contracts Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractsWidgetCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Contracts Widget Details Title", spendingPage.getWidgetDetailTitle(), "Contracts Trust & Agency Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Contracts Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractsWidgetAmountFromDB);
	
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
	
	@And("^the System displays Other Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateOtherSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String spendingDetailAmount =  DatabaseUtil.getOtherSpendingDetailsAmount(year,'B');
		Integer spendingDetailsCountFromDB = DatabaseUtil.getOtherSpendingDetailsCount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		assertFieldContainsText("Other Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Other Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Other Spending Transactions");
		assertFieldContainsText("Other Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Agencies Widget Details
		spendingPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Trust & Agency Spending Agencies Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Agencies Widget Details Title", spendingPage.getWidgetDetailTitle(), "Agencies Other Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Agencies Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories Widget Details
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Trust & Agency Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Other Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors Widget Details
		spendingPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Trust & Agency Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), spendingDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Prime Vendors Other Spending Transactions");
		assertFieldContainsText("Trust & Agency Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		softAssertion.assertAll();
	}

}
