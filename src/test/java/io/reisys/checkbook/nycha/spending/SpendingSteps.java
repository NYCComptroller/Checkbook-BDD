package io.reisys.checkbook.nycha.spending;


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
	
	@Given("^I navigate to OGE Page$")
	public void navigateTopayrollPage() {
		homePage.open();
		spendingPage.navigateToOGEPage();		       	
	}
	
	@When("^I select Nycha from  OGE drop down$")
	public void navigateToNYCHAPage()  {
		spendingPage.navigateToNYCHAPage();
			
	}
	
	@Then("^I navigate to Spending Page$")
	public void navigateToSpendingPage() {
		
		spendingPage.navigateToSpendingPage();
	
	}

	
	//----------------------- Total Spending Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Nycha Total Spending  Widget and Visualization titles for \"([^\"]*)\"$")
	public void validateSpendingInformation(String yearSelected) throws Exception {
		String spendingAmountFromDB = DatabaseUtil.getNYCHASpendingAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		assertFieldContainsText("Top Navigation Spending Amount", spendingPage.getSpendingAmount(), spendingAmountFromDB);
		assertFieldContainsText("Bottom Navigation Total Spending Amount", spendingPage.getBottomNavSpendingAmount(), spendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Total Spending Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Total Spending Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Nycha Total Spending Widgets count for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer totalCheckswidgetCountDB = DatabaseUtil.getNYCHATotalSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , totalCheckswidgetCountDB.toString());
		
		//Verify Total Industries
		Integer totalIndustriesWidgetCountDB = DatabaseUtil.getNYCHATotalSpendingIndustriesCount(year, 'B');
		assertFieldContainsText("Number of Industries", spendingPage.getWidgetTotalCount("Industries") , totalIndustriesWidgetCountDB.toString());
		
		//Verify Total FundingSource
				Integer totalFundingSourceWidgetCountDB = DatabaseUtil.getNYCHATotalSpendingFundingSourceCount(year, 'B');
				assertFieldHasText("Number of Funding Sources", spendingPage.getWidgetTotalCount("Funding Sources") , totalFundingSourceWidgetCountDB.toString());
			
		
		//Verify Expense Categories
		Integer totalExpenseCategoriesWidgetCountDB = DatabaseUtil.getNYCHATotalSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , totalExpenseCategoriesWidgetCountDB.toString());
		
		//Verify  Vendors
		Integer totalVendorsWidgetCountDB = DatabaseUtil.getNYCHATotalSpendingVendorsCount(year, 'B');
		assertFieldContainsText("Number of Vendors", spendingPage.getWidgetTotalCount("Vendors") , totalVendorsWidgetCountDB.toString());
		
		//Verify Contracts
		Integer totalContractsWidgetCountDB = DatabaseUtil.getNYCHATotalSpendingContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", spendingPage.getWidgetTotalCount("Contracts") , totalContractsWidgetCountDB.toString());
		//Verify ResponsibilityCenter 
		Integer totalRespCenterWidgetCountDB = DatabaseUtil.getNYCHATotalSpendingRespCentersCount(year, 'B');
		assertFieldContainsText("Number of Responsibility Centers", spendingPage.getWidgetTotalCount("Responsibility Centers") , totalRespCenterWidgetCountDB.toString());
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays Nycha Total Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateTotalSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String totalSpendingAmount =  DatabaseUtil.getNYCHATotalSpendingDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		Integer totalTransactionCountFromDB = DatabaseUtil.getNYCHATotalSpendingTransactionCount(year,'B');
		assertFieldContainsText("Total Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Total Spending Transactions");
		assertFieldContainsText("Total Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		
		//Verify Total Industries Widget Details
		spendingPage.navigateToWidgetDetails("Industries");
		assertFieldContainsText("Total Spending Industries Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Industries Widget Details Title", spendingPage.getWidgetDetailTitle(), "Industries Total Spending Transactions");
		assertFieldContainsText("Total Spending Industries Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Total Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Total Spending Transactions");
		assertFieldContainsText("Total Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Vendors
		spendingPage.navigateToWidgetDetails("Vendors");
		assertFieldContainsText("Total Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Vendors Total Spending Transactions");
		assertFieldContainsText("Total Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Contracts
		spendingPage.navigateToWidgetDetails("Contracts");
		//Integer totalContractsWidgetCountFromDB = DatabaseUtil.getTotalSpendingContractsDetailsCount(year,'B');
		assertFieldContainsText("Total Spending Contracts Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Contracts Widget Details Title", spendingPage.getWidgetDetailTitle(), "Contracts Total Spending Transactions");
		assertFieldContainsText("Total Spending Contracts Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Total Funding source Widget Details
		spendingPage.navigateToWidgetDetails("Funding Sources");
		assertFieldContainsText("Total Spending Funding Source Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Funding Source Widget Details Title", spendingPage.getWidgetDetailTitle(), "Funding Sources Total Spending Transactions");
		assertFieldContainsText("Total Spending Funding Sources Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		//Verify Responsibility Centers
		spendingPage.navigateToWidgetDetails("Responsibility Centers");
		assertFieldContainsText("Total Spending Responsibility Centers Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , totalTransactionCountFromDB.toString());
		assertFieldHasText("Total Spending Responsibility Centers Widget Details Title", spendingPage.getWidgetDetailTitle(), "Responsibility Centers Total Spending Transactions");
		assertFieldContainsText("Total Spending Responsibility Centers Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , totalSpendingAmount);
		spendingPage.navigateToSubTabType("Total");
		
		softAssertion.assertAll();
	}

	
	//----------------------- Payroll Spending Sub Tab Steps -------------------------------------
	@Then("^I navigate to Payroll Spending sub tab$")
	public void navigateToSpendingPayrollPage() {
		
		spendingPage.navigateToSubTabType("Payroll");
	
	}
	@Then("^the System displays Payroll Spending Information for \"([^\"]*)\"$")
	public void validatePayrollSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String payrollSpendingAmountFromDB = DatabaseUtil.getNYCHAPayrollSpendingAmount(selectedYear, 'B');
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
		
		//Verify Total Departments
		Integer payrollSpendingDepartmentsWidgetCountFromDB = DatabaseUtil. getNYCHAPayrollSpendingDepartmentsCount(year, 'B');
		assertFieldContainsText("Number of Departments", spendingPage.getWidgetTotalCount("Departments") , payrollSpendingDepartmentsWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer payrollSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getNYCHAPayrollSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , payrollSpendingExpenseCategoriesWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	@And("^the System displays Payroll Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validatePayrollSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String payrollSpendingAmountFromDB =  DatabaseUtil.getNYCHAPayrollSpendingDetailsAmount(year,'B');
		Integer payrollSpendingCountFromDB = DatabaseUtil.getNYCHAPayrollSpendingDetailsCount(year,'B');
		
		//Verify  Departments Widget Details
		spendingPage.navigateToWidgetDetails("Departments");
		assertFieldContainsText("Payroll Spending Departments Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , payrollSpendingCountFromDB.toString());
		assertFieldHasText("Payroll Spending Departments Widget Details Title", spendingPage.getWidgetDetailTitle(), "Departments Payroll Spending Transactions");
		assertFieldContainsText("Payroll Spending Departments Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , payrollSpendingAmountFromDB);
		spendingPage.navigateToSubTabType("Payroll");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Payroll Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , payrollSpendingCountFromDB.toString());
		assertFieldHasText("Payroll Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Payroll Spending Transactions");
		assertFieldContainsText("Payroll Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , payrollSpendingAmountFromDB);
		

		softAssertion.assertAll();
	}
	
	
	
	//----------------------- Contract Spending Sub Tab Steps -------------------------------------

	
	@Then("^I navigate to Contract Spending sub tab$")
	
	
	public void navigateToSpendingContractPage() {
		
		spendingPage.navigateToSubTabType("Payroll");
	
	}
	@Then("^the System displays Contract Spending Information for \"([^\"]*)\"$")
	public void validateContractSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String contractSpendingAmountFromDB = DatabaseUtil.getNYCHAContractSpendingAmount(selectedYear, 'B');
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
		Integer contractSpendingChecksWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , contractSpendingChecksWidgetCountFromDB.toString());
		
		//Verify Total Industries
		Integer contractSpendingIndustriesWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingIndustriesCount(year, 'B');
		assertFieldContainsText("Number of Industries", spendingPage.getWidgetTotalCount("Industries") , contractSpendingIndustriesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer contractSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , contractSpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Vendors
		Integer contractSpendingVendorsWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingVendorsCount(year, 'B');
		assertFieldContainsText("Number of Vendors", spendingPage.getWidgetTotalCount("Vendors") , contractSpendingVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer contractSpendingContractsWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", spendingPage.getWidgetTotalCount("Contracts") , contractSpendingContractsWidgetCountFromDB.toString());
	
		//Verify Funding Source
				Integer contractSpendingFundingSourceWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingFundingSourceCount(year, 'B');
				assertFieldContainsText("Number of Funding Sources", spendingPage.getWidgetTotalCount("Funding Sources") , contractSpendingFundingSourceWidgetCountFromDB.toString());
				
				//Verify Responsibility Centers
				Integer contractSpendingRespCentersWidgetCountFromDB = DatabaseUtil.getNYCHAContractSpendingRespCentersCount(year, 'B');
				assertFieldContainsText("Number of Responsibility Centers", spendingPage.getWidgetTotalCount("Responsibility Centers") , contractSpendingRespCentersWidgetCountFromDB.toString());
				
		softAssertion.assertAll();
	}
	
	@And("^the System displays Contract Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateContractSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String contractSpendingDetailAmount =  DatabaseUtil.getNYCHAContractSpendingAmount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		Integer contractSpendingDetailsCountFromDB = DatabaseUtil.getNYCHAContractSpendingTransactionCount(year,'B');
		assertFieldContainsText("Contract Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Industries Widget Details
		spendingPage.navigateToWidgetDetails("Industries");
		assertFieldContainsText("Contract Spending Industries Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Industries Widget Details Title", spendingPage.getWidgetDetailTitle(), "Industries Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Industries Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Contract Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Contract Spending Transactions");
		assertFieldContainsText("Contract Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors
		spendingPage.navigateToWidgetDetails("Vendors");
		assertFieldContainsText("Contract Spending Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), contractSpendingDetailsCountFromDB.toString());
		assertFieldHasText("Contract Spending Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Vendors Contract Spending Transactions");
		assertFieldContainsText("Contract Spending  Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , contractSpendingDetailAmount);
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
	
	//----------------------- Section 8 Spending Sub Tab Steps -------------------------------------
	
	@Then("^I navigate to Section 8 Spending sub tab$")
	public void navigateToSpendingSection8Page() {
		
		spendingPage.navigateToSubTabType("Section 8");
	}
	@Then("^the System displays Section 8 Spending Information for \"([^\"]*)\"$")
	public void validateSection8SpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String section8SpendingAmountFromDB = DatabaseUtil.getNYCHASection8SpendingAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Section 8 Spending Amount", spendingPage.getBottomNavSpendingAmount(), section8SpendingAmountFromDB);

		assertListOfValues("Visualization Titles", spendingPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Section 8 Spending Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", spendingPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Section 8 Spending Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	
	@Then("^the System displays Section 8 Spending Widgets for \"([^\"]*)\"$")
	public void validateSection8SpendingWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer Section8SpendingChecksWidgetCountFromDB = DatabaseUtil.getNYCHASection8SpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , Section8SpendingChecksWidgetCountFromDB.toString());
		
		
		//Verify Expense Categories
		Integer Section8SpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getNYCHASection8SpendingExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , Section8SpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify  Vendors
		Integer Section8SpendingVendorsWidgetCountFromDB = DatabaseUtil.getNYCHASection8SpendingVendorsCount(year, 'B');
		assertFieldContainsText("Number of Vendors", spendingPage.getWidgetTotalCount("Vendors") , Section8SpendingVendorsWidgetCountFromDB.toString());
		
		//Verify Funding Source
		Integer Section8SpendingContractsWidgetCountFromDB = DatabaseUtil.getNYCHASection8SpendingFundingSourceCount(year, 'B');
		assertFieldContainsText("Number of Funding Sources", spendingPage.getWidgetTotalCount("Funding Sources") , Section8SpendingContractsWidgetCountFromDB.toString());
	
		//Verify Resp center
				Integer Section8SpendingRespCenterWidgetCountFromDB = DatabaseUtil.getNYCHASection8SpendingRespCentersCount(year, 'B');
				assertFieldContainsText("Number of Responsibility Centers", spendingPage.getWidgetTotalCount("Responsibility Centers") , Section8SpendingRespCenterWidgetCountFromDB.toString());
			
		softAssertion.assertAll();
	}
	
	@And("^the System displays Section 8 Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateSection8SpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String spendingDetailAmount =  DatabaseUtil.getNYCHASection8SpendingDetailsAmount(year,'B');
		Integer spendingDetailsCountFromDB = DatabaseUtil.getNYCHASection8SpendingTransactionCount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		assertFieldContainsText("Section 8 Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Section 8 Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Section 8 Spending Transactions");
		assertFieldContainsText("Section 8 Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Funding Source Widget Details
		spendingPage.navigateToWidgetDetails("Funding Source");
		assertFieldContainsText("Section 8 Spending Funding Source Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Section 8 Spending Funding Source Widget Details Title", spendingPage.getWidgetDetailTitle(), "Funding Sources Section 8 Spending Transactions");
		assertFieldContainsText("Section 8 Spending Funding Source Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Section 8 Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Section 8 Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Section 8 Spending Transactions");
		assertFieldContainsText("Section 8 Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Vendors
		spendingPage.navigateToWidgetDetails("Vendors");
		assertFieldContainsText("Section 8 Spending Prime Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), spendingDetailsCountFromDB.toString());
		assertFieldHasText("Section 8 Spending Prime Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Vendors Section 8 Spending Transactions");
		assertFieldContainsText("Section 8 Spending Prime Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Contract");
		
		//Verify Contracts
		spendingPage.navigateToWidgetDetails("Contracts");
		assertFieldContainsText("Section 8 Spending Contracts Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Section 8 Spending Contracts Widget Details Title", spendingPage.getWidgetDetailTitle(), "Contracts Section 8 Spending Transactions");
		assertFieldContainsText("Section 8 Spending Contracts Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
	
		softAssertion.assertAll();
	}
	
	//---------------  Other Spending Sub Tab steps ----------------------------------
	
	@Then("^I navigate to Other Spending sub tab$")
	public void navigateToSpendingOtherPage() {
		
		spendingPage.navigateToSubTabType("Other");
	}
	@Then("^the System displays Other Spending Information for \"([^\"]*)\"$")
	public void validateOtherSpendingInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String otherSpendingAmountFromDB = DatabaseUtil.getNYCHAOtherSpendingAmount(selectedYear, 'B');
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
		Integer otherSpendingChecksWidgetCountFromDB = DatabaseUtil.getNYCHAOtherSpendingChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", spendingPage.getWidgetTotalCount("Checks") , otherSpendingChecksWidgetCountFromDB.toString());
		
		//Verify Total Funding Sources
		Integer otherSpendingAgenciesWidgetCountFromDB = DatabaseUtil.getNYCHAOtherSpendingFundingSourceCount(year, 'B');
		assertFieldContainsText("Number of Funding Sources", spendingPage.getWidgetTotalCount("Funding Sources") , otherSpendingAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer otherSpendingExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getNYCHAOtherSpendingExpCategoriesCount(year, 'B');
		assertFieldHasText("Number of Expense Categories", spendingPage.getWidgetTotalCount("Expense Categories") , otherSpendingExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify  Vendors
		Integer otherSpendingPrimeVendorsWidgetCountFromDB = DatabaseUtil.getNYCHAOtherSpendingVendorsCount(year, 'B');
		assertFieldContainsText("Number of Vendors", spendingPage.getWidgetTotalCount("Vendors") , otherSpendingPrimeVendorsWidgetCountFromDB.toString());

		//Verify Resp Centers
				Integer otherSpendingRespCentersWidgetCountFromDB = DatabaseUtil.getNYCHAOtherSpendingRespCentersCount(year, 'B');
				assertFieldContainsText("Number of Responsibility Centers", spendingPage.getWidgetTotalCount("Responsibility Centers") , otherSpendingRespCentersWidgetCountFromDB.toString());

		softAssertion.assertAll();
	}
	
	@And("^the System displays Other Spending Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateOtherSpendingWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String spendingDetailAmount =  DatabaseUtil.getNYCHAOtherSpendingDetailsAmount(year,'B');
		Integer spendingDetailsCountFromDB = DatabaseUtil.getNYCHAOtherSpendingTransactionCount(year,'B');
		
		//Verify Total Checks Widget Details
		spendingPage.navigateToWidgetDetails("Checks");
		assertFieldContainsText("Other Spending Checks Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Other Spending Checks Widget Details Title", spendingPage.getWidgetDetailTitle(), "Checks Other Spending Transactions");
		assertFieldContainsText("Other Spending Checks Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Other");
		
		
		//Verify Total Funding Sources Widget Details
		spendingPage.navigateToWidgetDetails("Funding Sources");
		assertFieldContainsText("Other Spending Funding Sources Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Other Spending Funding Sources Widget Details Title", spendingPage.getWidgetDetailTitle(), "Funding Sources Other Spending Transactions");
		assertFieldContainsText("Other Spending Funding Sources Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Other");
		
		//Verify Expense Categories Widget Details
		spendingPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Other Spending Expense Categories Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails() , spendingDetailsCountFromDB.toString());
		assertFieldHasText("Other Spending Expense Categories Widget Details Title", spendingPage.getWidgetDetailTitle(), "Expense Categories Other Spending Transactions");
		assertFieldContainsText("Other Spending Expense Categories Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Other");
		
		//Verify  Vendors Widget Details
		spendingPage.navigateToWidgetDetails("Vendors");
		assertFieldContainsText("Other Spending  Vendors Widget Detail Transaction Count", spendingPage.getTotalCountForWidgetDetails(), spendingDetailsCountFromDB.toString());
		assertFieldHasText("Other Spending  Vendors Widget Details Title", spendingPage.getWidgetDetailTitle(), "Vendors Other Spending Transactions");
		assertFieldContainsText("Other Spending Vendors Widget Detail Transaction Amount", spendingPage.getTransactionAmount() , spendingDetailAmount);
		spendingPage.navigateToSubTabType("Other");
		
		softAssertion.assertAll();
	}

}
