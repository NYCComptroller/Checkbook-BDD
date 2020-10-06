package io.reisys.checkbook.contracts;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class ContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ContractsPage ContractsPage;
	
	@Given("^I navigate to Contracts Page$")
	public void navigateToContractsPage() {
		homePage.open();
		ContractsPage.navigateToContractsPage();
	}
    @When("^I select \"([^\"]*)\" for getting data$")
	
	public void selectYear(String year) {
		homePage.selectYear(year);
	}
	
	@And("I navigate to \"([^\"]*)\" Expense Contracts sub tab")
	public void navigateToSubTab(String subTabType) {
		ContractsPage.navigateToSubTabType(subTabType);
		assertFieldContainsText("Selected Sub Tab", ContractsPage.getCurrentSelectedSubTabType(), subTabType);
	}
	
	//----------------------- Total Contracts Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Active Expense Contracts Information for \"([^\"]*)\"$")
	public void validateContractsInformation(String yearSelected) throws Exception {
		String ContractsAmountFromDB = DatabaseUtil.getAEContractsAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		String TopContractsAmountFromDB = DatabaseUtil.getContractsTopAmount(ExecutionContext.getJsonData().get(yearSelected).getAsInt(), 'B');
		
		assertFieldContainsText("Top Navigation Contracts Amount", ContractsPage.getContractsAmount(), TopContractsAmountFromDB);
		assertFieldContainsText("Bottom Navigation Total Contracts Amount", ContractsPage.getBottomNavContractsAmount(), ContractsAmountFromDB);

		assertListOfValues("Visualization Titles", ContractsPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Active Expense Contracts Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", ContractsPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Active Expense Contracts Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@And("^the System displays Active Expense Contracts Widgets for \"([^\"]*)\"$")
	public void validateTotalContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Active Expense Master Agreements
		Integer activeExpenseMasterswidgetCountDB = DatabaseUtil.getAEMasterContractsCount(year, 'B');
		assertFieldContainsText("Number of Master Agreements", ContractsPage.getWidgetTotalCount("Master Agreement Contracts") , activeExpenseMasterswidgetCountDB.toString());
		//Verify Active Expense Master Agreements Modifications
		Integer activeExpenseMasterModsWidgetCountDB = DatabaseUtil.getAEMasterContractsModificationCount(year, 'B');
		assertFieldContainsText("Number of Master Agreements Modifications", ContractsPage.getWidgetTotalCount("Master Agreement Modifications") , activeExpenseMasterModsWidgetCountDB.toString());
		
		//Verify Active Expense Contracts Agreements
		Integer totalContractsWidgetCountDB = DatabaseUtil.getAEContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts ", ContractsPage.getWidgetTotalCount("Stand Alone Contracts") , totalContractsWidgetCountDB.toString());
		
		//Verify Active Expense Contracts Agreements Modifications
		Integer totalContractModWidgetCountDB = DatabaseUtil.getAEContractsModificationCount(year, 'B');
		assertFieldContainsText("Number of Contracts Modifications", ContractsPage.getWidgetTotalCount("Contract Modifications") , totalContractModWidgetCountDB.toString());
						
		//Verify Active Expense Agencies
		Integer totalAgenciesWidgetCountDB = DatabaseUtil.getAEContractsAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", ContractsPage.getWidgetTotalCount("Agencies") , totalAgenciesWidgetCountDB.toString());
		
		//Verify Active Expense Prime Vendors
		Integer totalPrimeVendorsWidgetCountDB = DatabaseUtil.getAEContractsPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", ContractsPage.getWidgetTotalCount("Prime Vendors") , totalPrimeVendorsWidgetCountDB.toString());
				
		
		//Verify Active Expense Contracts By AwardMethod
		Integer totalContractsIndWidgetCountDB = DatabaseUtil.getAEContractsAwardMethodsCount(year, 'B');
		assertFieldContainsText("Number of Contracts AwardMethod", ContractsPage.getWidgetTotalCount("Award Methods") ,totalContractsIndWidgetCountDB.toString());
					
		//Verify Active Expense Industries
		Integer totalIndustriesWidgetCountDB = DatabaseUtil.getAEContractsIndustriesCount(year, 'B');
		assertFieldContainsText("Number of Industries", ContractsPage.getWidgetTotalCount("Contracts") , totalIndustriesWidgetCountDB.toString());
		
		
		//Verify Active Expense Contracts By Size
		Integer totalContractsSizeWidgetCountDB = DatabaseUtil.getAEContractsSizeCount(year, 'B');
		assertFieldContainsText("Number of Contracts By Size", ContractsPage.getWidgetTotalCount("Contracts") , totalContractsSizeWidgetCountDB.toString());
	
		
		
		softAssertion.assertAll();
	}
	/*
	@And("^the System displays Total Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateTotalContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String totalContractsAmount =  DatabaseUtil.getTotalContractsDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		ContractsPage.navigateToWidgetDetails("Checks");
		Integer totalChecksWidgetCountFromDB = DatabaseUtil.getTotalContractsChecksCount(year,'B');
		assertFieldContainsText("Total Contracts Checks Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Contracts Checks Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Checks Total Contracts Transactions");
		assertFieldContainsText("Total Contracts Checks Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , totalContractsAmount);
		ContractsPage.navigateToSubTabType("Total");
		
		
		//Verify Total Agencies Widget Details
		ContractsPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Total Contracts Agencies Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Contracts Agencies Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Agencies Total Contracts Transactions");
		assertFieldContainsText("Total Contracts Agencies Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , totalContractsAmount);
		ContractsPage.navigateToSubTabType("Total");
		
		//Verify Expense Categories
		ContractsPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Total Contracts Expense Categories Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Contracts Expense Categories Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Expense Categories Total Contracts Transactions");
		assertFieldContainsText("Total Contracts Expense Categories Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , totalContractsAmount);
		ContractsPage.navigateToSubTabType("Total");
		
		//Verify Prime Vendors
		ContractsPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Total Contracts Prime Vendors Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails(), totalChecksWidgetCountFromDB.toString());
		assertFieldHasText("Total Contracts Prime Vendors Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Prime Vendors Total Contracts Transactions");
		assertFieldContainsText("Total Contracts Prime Vendors Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , totalContractsAmount);
		ContractsPage.navigateToSubTabType("Total");
		
		//Verify Contracts
		ContractsPage.navigateToWidgetDetails("Contracts");
		Integer totalContractsWidgetCountFromDB = DatabaseUtil.getTotalContractsContractsDetailsCount(year,'B');
		assertFieldContainsText("Total Contracts Contracts Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , totalContractsWidgetCountFromDB.toString());
		assertFieldHasText("Total Contracts Contracts Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Contracts Total Contracts Transactions");
		assertFieldContainsText("Total Contracts Contracts Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , totalContractsAmount);
	
		softAssertion.assertAll();
	}

	
	//----------------------- Payroll Contracts Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Payroll Contracts Information for \"([^\"]*)\"$")
	public void validatePayrollContractsInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String payrollContractsAmountFromDB = DatabaseUtil.getPayrollContractsAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Payroll Contracts Amount", ContractsPage.getBottomNavContractsAmount(), payrollContractsAmountFromDB);

		assertListOfValues("Visualization Titles", ContractsPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Contracts Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", ContractsPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Payroll Contracts Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Payroll Contracts Widgets for \"([^\"]*)\"$")
	public void validatePayrollContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Agencies
		Integer payrollContractsAgenciesWidgetCountFromDB = DatabaseUtil.getPayrollAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", ContractsPage.getWidgetTotalCount("Agencies") , payrollContractsAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer payrollContractsExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getPayrollContractsExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", ContractsPage.getWidgetTotalCount("Expense Categories") , payrollContractsExpenseCategoriesWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	@And("^the System displays Payroll Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validatePayrollContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String payrollContractsAmountFromDB =  DatabaseUtil.getPayrollContractsDetailsAmount(year,'B');
		Integer payrollContractsCountFromDB = DatabaseUtil.getPayrollContractsDetailsCount(year,'B');
		
		//Verify Total Agencies Widget Details
		ContractsPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Payroll Contracts Agencies Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , payrollContractsCountFromDB.toString());
		assertFieldHasText("Payroll Contracts Agencies Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Agencies Payroll Contracts Transactions");
		assertFieldContainsText("Payroll Contracts Agencies Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , payrollContractsAmountFromDB);
		ContractsPage.navigateToSubTabType("Payroll");
		
		//Verify Expense Categories
		ContractsPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Payroll Contracts Expense Categories Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , payrollContractsCountFromDB.toString());
		assertFieldHasText("Payroll Contracts Expense Categories Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Expense Categories Payroll Contracts Transactions");
		assertFieldContainsText("Payroll Contracts Expense Categories Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , payrollContractsAmountFromDB);
		

		softAssertion.assertAll();
	}
	
	//----------------------- Capital Contracts Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Capital Contracts Information for \"([^\"]*)\"$")
	public void validateCaptialContractsInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String capitalContractsAmountFromDB = DatabaseUtil.getCapitalContractsAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Capital Contracts Amount", ContractsPage.getBottomNavContractsAmount(), capitalContractsAmountFromDB);

		assertListOfValues("Visualization Titles", ContractsPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Capital Contracts Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", ContractsPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Capital Contracts Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Capital Contracts Widgets for \"([^\"]*)\"$")
	public void validateCapitalContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer capitalContractsChecksWidgetCountFromDB = DatabaseUtil.getCapitalContractsChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", ContractsPage.getWidgetTotalCount("Checks") , capitalContractsChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer capitalContractsAgenciesWidgetCountFromDB = DatabaseUtil.getCapitalContractsAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", ContractsPage.getWidgetTotalCount("Agencies") , capitalContractsAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer capitalContractsExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getCapitalContractsExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", ContractsPage.getWidgetTotalCount("Expense Categories") , capitalContractsExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer capitalContractsPrimeVendorsWidgetCountFromDB = DatabaseUtil.getCapitalContractsPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", ContractsPage.getWidgetTotalCount("Prime Vendors") , capitalContractsPrimeVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer capitalContractsContractsWidgetCountFromDB = DatabaseUtil.getCapitalContractsContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", ContractsPage.getWidgetTotalCount("Contracts") , capitalContractsContractsWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	@And("^the System displays Capital Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateCapitalContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String capitalContractsAmount =  DatabaseUtil.getCapitalContractsContractsDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		ContractsPage.navigateToWidgetDetails("Checks");
		Integer capitalContractsDetailsCountFromDB = DatabaseUtil.getCapitalContractsDetailsCount(year,'B');
		assertFieldContainsText("Capital Contracts Checks Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , capitalContractsDetailsCountFromDB.toString());
		assertFieldHasText("Capital Contracts Checks Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Checks Capital Contracts Transactions");
		assertFieldContainsText("Capital Contracts Checks Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , capitalContractsAmount);
		ContractsPage.navigateToSubTabType("Capital");
		
		
		//Verify Total Agencies Widget Details
		ContractsPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Capital Contracts Agencies Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , capitalContractsDetailsCountFromDB.toString());
		assertFieldHasText("Capital Contracts Agencies Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Agencies Capital Contracts Transactions");
		assertFieldContainsText("Capital Contracts Agencies Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , capitalContractsAmount);
		ContractsPage.navigateToSubTabType("Capital");
		
		//Verify Expense Categories
		ContractsPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Capital Contracts Expense Categories Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , capitalContractsDetailsCountFromDB.toString());
		assertFieldHasText("Capital Contracts Expense Categories Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Expense Categories Capital Contracts Transactions");
		assertFieldContainsText("Capital Contracts Expense Categories Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , capitalContractsAmount);
		ContractsPage.navigateToSubTabType("Capital");
		
		//Verify Prime Vendors
		ContractsPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Capital Contracts Prime Vendors Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails(), capitalContractsDetailsCountFromDB.toString());
		assertFieldHasText("Capital Contracts Prime Vendors Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Prime Vendors Capital Contracts Transactions");
		assertFieldContainsText("Capital Contracts Prime Vendors Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , capitalContractsAmount);
		ContractsPage.navigateToSubTabType("Capital");
		
		//Verify Contracts
		ContractsPage.navigateToWidgetDetails("Contracts");
		Integer capitalContractsWidgetCountFromDB = DatabaseUtil.getCapitalContractsContractsDetailsCount(year,'B');
		String capitalContractsWidgetAmountFromDB = DatabaseUtil.getCapitalContractsContractsContractsDetailsAmount(year, 'B');
		assertFieldContainsText("Capital Contracts Contracts Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , capitalContractsWidgetCountFromDB.toString());
		assertFieldHasText("Capital Contracts Contracts Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Contracts Capital Contracts Transactions");
		assertFieldContainsText("Capital Contracts Contracts Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , capitalContractsWidgetAmountFromDB);
	
		softAssertion.assertAll();
	}
	
	//----------------------- Contract Contracts Sub Tab Steps -------------------------------------
	
	@Then("^the System displays Contract Contracts Information for \"([^\"]*)\"$")
	public void validateContractContractsInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String contractContractsAmountFromDB = DatabaseUtil.getContractContractsAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Contract Contracts Amount", ContractsPage.getBottomNavContractsAmount(), contractContractsAmountFromDB);

		assertListOfValues("Visualization Titles", ContractsPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Contract Contracts Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", ContractsPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Contract Contracts Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Contract Contracts Widgets for \"([^\"]*)\"$")
	public void validateContractContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer contractContractsChecksWidgetCountFromDB = DatabaseUtil.getContractContractsChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", ContractsPage.getWidgetTotalCount("Checks") , contractContractsChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer contractContractsAgenciesWidgetCountFromDB = DatabaseUtil.getContractContractsAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", ContractsPage.getWidgetTotalCount("Agencies") , contractContractsAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer contractContractsExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getContractContractsExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", ContractsPage.getWidgetTotalCount("Expense Categories") , contractContractsExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer contractContractsPrimeVendorsWidgetCountFromDB = DatabaseUtil.getContractContractsPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", ContractsPage.getWidgetTotalCount("Prime Vendors") , contractContractsPrimeVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer contractContractsContractsWidgetCountFromDB = DatabaseUtil.getContractContractsContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", ContractsPage.getWidgetTotalCount("Contracts") , contractContractsContractsWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	@And("^the System displays Contract Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateContractContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String contractContractsDetailAmount =  DatabaseUtil.getContractsContractsDetailsAmount(year,'B');
		
		//Verify Total Checks Widget Details
		ContractsPage.navigateToWidgetDetails("Checks");
		Integer contractContractsDetailsCountFromDB = DatabaseUtil.getContractContractsDetailsCount(year,'B');
		assertFieldContainsText("Contract Contracts Checks Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , contractContractsDetailsCountFromDB.toString());
		assertFieldHasText("Contract Contracts Checks Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Checks Contract Contracts Transactions");
		assertFieldContainsText("Contract Contracts Checks Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , contractContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Agencies Widget Details
		ContractsPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Contract Contracts Agencies Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , contractContractsDetailsCountFromDB.toString());
		assertFieldHasText("Contract Contracts Agencies Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Agencies Contract Contracts Transactions");
		assertFieldContainsText("Contract Contracts Agencies Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , contractContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories
		ContractsPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Contract Contracts Expense Categories Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , contractContractsDetailsCountFromDB.toString());
		assertFieldHasText("Contract Contracts Expense Categories Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Expense Categories Contract Contracts Transactions");
		assertFieldContainsText("Contract Contracts Expense Categories Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , contractContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors
		ContractsPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Contract Contracts Prime Vendors Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails(), contractContractsDetailsCountFromDB.toString());
		assertFieldHasText("Contract Contracts Prime Vendors Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Prime Vendors Contract Contracts Transactions");
		assertFieldContainsText("Contract Contracts Prime Vendors Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , contractContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Contracts
		ContractsPage.navigateToWidgetDetails("Contracts");
		Integer contractsWidgetCountFromDB = DatabaseUtil.getContractContractsContractsDetailsCount(year,'B');
		String contractsWidgetAmountFromDB = DatabaseUtil.getContractsContractsContractsDetailsAmount(year, 'B');
		assertFieldContainsText("Contract Contracts Contracts Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , contractsWidgetCountFromDB.toString());
		assertFieldHasText("Contract Contracts Contracts Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Contracts Contracts Transactions");
		assertFieldContainsText("Contract Contracts Contracts Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , contractsWidgetAmountFromDB);
	
		softAssertion.assertAll();
	}
	
	//----------------------- Trust & Agency Contracts Sub Tab Steps -------------------------------------

	@Then("^the System displays Trust and Agency Contracts Information for \"([^\"]*)\"$")
	public void validateTrustAndAgencyContractsInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String otherContractsAmountFromDB = DatabaseUtil.getTrustAgencyContractsAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Trust and Agency Contracts Amount", ContractsPage.getBottomNavContractsAmount(), otherContractsAmountFromDB);

		assertListOfValues("Visualization Titles", ContractsPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Trust and Agency Contracts Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", ContractsPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Trust and Agency Contracts Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	
	@Then("^the System displays Trust and Agency Contracts Widgets for \"([^\"]*)\"$")
	public void validateTrustAndAgencyContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer trustAndAgencyContractsChecksWidgetCountFromDB = DatabaseUtil.getTrustAgencyContractsChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", ContractsPage.getWidgetTotalCount("Checks") , trustAndAgencyContractsChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer trustAndAgencyContractsAgenciesWidgetCountFromDB = DatabaseUtil.getTrustAgencyContractsAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", ContractsPage.getWidgetTotalCount("Agencies") , trustAndAgencyContractsAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer trustAndAgencyContractsExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getTrustAgencyContractsExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", ContractsPage.getWidgetTotalCount("Expense Categories") , trustAndAgencyContractsExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer trustAndAgencyContractsPrimeVendorsWidgetCountFromDB = DatabaseUtil.getTrustAgencyContractsPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", ContractsPage.getWidgetTotalCount("Prime Vendors") , trustAndAgencyContractsPrimeVendorsWidgetCountFromDB.toString());
		
		//Verify Contracts
		Integer trustAndAgencyContractsContractsWidgetCountFromDB = DatabaseUtil.getTrustAgencyContractsContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", ContractsPage.getWidgetTotalCount("Contracts") , trustAndAgencyContractsContractsWidgetCountFromDB.toString());
	
		softAssertion.assertAll();
	}
	
	@And("^the System displays Trust and Agency Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateTrustAndAgencyContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String ContractsDetailAmount =  DatabaseUtil.getTrustAgencyContractsDetailsAmount(year,'B');
		Integer ContractsDetailsCountFromDB = DatabaseUtil.getTrustAgencyContractsDetailsCount(year,'B');
		
		//Verify Total Checks Widget Details
		ContractsPage.navigateToWidgetDetails("Checks");
		assertFieldContainsText("Trust & Agency Contracts Checks Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Checks Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Checks Trust & Agency Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Checks Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Agencies Widget Details
		ContractsPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Trust & Agency Contracts Agencies Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Agencies Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Agencies Trust & Agency Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Agencies Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories
		ContractsPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Trust & Agency Contracts Expense Categories Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Expense Categories Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Expense Categories Trust & Agency Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Expense Categories Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors
		ContractsPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Trust & Agency Contracts Prime Vendors Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails(), ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Prime Vendors Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Prime Vendors Trust & Agency Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Prime Vendors Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Contracts
		ContractsPage.navigateToWidgetDetails("Contracts");
		Integer contractsWidgetCountFromDB = DatabaseUtil.getTrustAgencyContractsContractsDetailsCount(year,'B');
		String contractsWidgetAmountFromDB = DatabaseUtil.getTrustAgencyContractsContractsDetailsAmount(year, 'B');
		assertFieldContainsText("Trust & Agency Contracts Contracts Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , contractsWidgetCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Contracts Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Contracts Trust & Agency Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Contracts Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , contractsWidgetAmountFromDB);
	
		softAssertion.assertAll();
	}
	
	//---------------  Other Contracts Sub Tab steps ----------------------------------
	
	@Then("^the System displays Other Contracts Information for \"([^\"]*)\"$")
	public void validateOtherContractsInformation(String yearSelected) throws Exception {
		int selectedYear = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String otherContractsAmountFromDB = DatabaseUtil.getOtherContractsAmount(selectedYear, 'B');
		assertFieldContainsText("Bottom Navigation Other Contracts Amount", ContractsPage.getBottomNavContractsAmount(), otherContractsAmountFromDB);

		assertListOfValues("Visualization Titles", ContractsPage.getVisualizationTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Other Contracts Visualization Titles").getAsJsonArray()));
	
		assertListOfValues("Widget Titles", ContractsPage.getWidgetTitles(), 
			CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Other Contracts Widget Titles").getAsJsonArray()));
	
		softAssertion.assertAll();
	}
	
	@Then("^the System displays Other Contracts Widgets for \"([^\"]*)\"$")
	public void validateOtherContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify Total Checks
		Integer otherContractsChecksWidgetCountFromDB = DatabaseUtil.getOtherContractsChecksCount(year, 'B');
		assertFieldContainsText("Number of Checks", ContractsPage.getWidgetTotalCount("Checks") , otherContractsChecksWidgetCountFromDB.toString());
		
		//Verify Total Agencies
		Integer otherContractsAgenciesWidgetCountFromDB = DatabaseUtil.getOtherContractsAgenciesCount(year, 'B');
		assertFieldContainsText("Number of Agencies", ContractsPage.getWidgetTotalCount("Agencies") , otherContractsAgenciesWidgetCountFromDB.toString());
		
		//Verify Expense Categories
		Integer otherContractsExpenseCategoriesWidgetCountFromDB = DatabaseUtil.getOtherContractsExpCategoriesCount(year, 'B');
		assertFieldContainsText("Number of Expense Categories", ContractsPage.getWidgetTotalCount("Expense Categories") , otherContractsExpenseCategoriesWidgetCountFromDB.toString());
		
		//Verify Prime Vendors
		Integer otherContractsPrimeVendorsWidgetCountFromDB = DatabaseUtil.getOtherContractsPrimeVendorsCount(year, 'B');
		assertFieldContainsText("Number of Prime Vendors", ContractsPage.getWidgetTotalCount("Prime Vendors") , otherContractsPrimeVendorsWidgetCountFromDB.toString());

		softAssertion.assertAll();
	}
	
	@And("^the System displays Other Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateOtherContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		String ContractsDetailAmount =  DatabaseUtil.getOtherContractsDetailsAmount(year,'B');
		Integer ContractsDetailsCountFromDB = DatabaseUtil.getOtherContractsDetailsCount(year,'B');
		
		//Verify Total Checks Widget Details
		ContractsPage.navigateToWidgetDetails("Checks");
		assertFieldContainsText("Other Contracts Checks Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Other Contracts Checks Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Checks Other Contracts Transactions");
		assertFieldContainsText("Other Contracts Checks Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		
		//Verify Total Agencies Widget Details
		ContractsPage.navigateToWidgetDetails("Agencies");
		assertFieldContainsText("Trust & Agency Contracts Agencies Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Agencies Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Agencies Other Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Agencies Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Expense Categories Widget Details
		ContractsPage.navigateToWidgetDetails("Expense Categories");
		assertFieldContainsText("Trust & Agency Contracts Expense Categories Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails() , ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Expense Categories Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Expense Categories Other Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Expense Categories Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		
		//Verify Prime Vendors Widget Details
		ContractsPage.navigateToWidgetDetails("Prime Vendors");
		assertFieldContainsText("Trust & Agency Contracts Prime Vendors Widget Detail Transaction Count", ContractsPage.getTotalCountForWidgetDetails(), ContractsDetailsCountFromDB.toString());
		assertFieldHasText("Trust & Agency Contracts Prime Vendors Widget Details Title", ContractsPage.getWidgetDetailTitle(), "Prime Vendors Other Contracts Transactions");
		assertFieldContainsText("Trust & Agency Contracts Prime Vendors Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , ContractsDetailAmount);
		ContractsPage.navigateToSubTabType("Contract");
		*/
		//softAssertion.assertAll();
		
		
	}


