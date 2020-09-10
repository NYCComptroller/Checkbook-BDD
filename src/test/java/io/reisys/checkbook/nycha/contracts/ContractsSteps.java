package io.reisys.checkbook.nycha.contracts;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;
import io.reisys.checkbook.utilities.DatabaseUtil2;

public class ContractsSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	ContractsPage contractsPage;
	
	@Given("^I navigate to OGE Page$")
	public void navigateTopayrollPage() {
		homePage.open();
		contractsPage.navigateToOGEPage();		       	
	}
	
	@When("^I select Nycha from  OGE drop down$")
	public void navigateToNYCHAPage()  {
		contractsPage.navigateToNYCHAPage();
		contractsPage.navigateToContractsPage();
			
	}
	
	@Then("^the System displays Nycha Contracts  Widget and Visualization titles for \"([^\"]*)\"$")
	public void validateContractsInformation(String yearSelected) throws Exception {
	//	int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		assertListOfValues("Visualization Titles", contractsPage.getVisualizationTitles(), 
					CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Contracts Visualization Titles").getAsJsonArray()));
		
		assertListOfValues("Widget Titles", contractsPage.getWidgetTitles(), 
				CommonUtility.convertJsonArrayToList(ExecutionContext.getJsonData().get("Contracts Widget Titles").getAsJsonArray()));
		
		softAssertion.assertAll();
	}
	
	@Then("^the System displays contracts Widgets count for \"([^\"]*)\"$")
	public void validateNychaContractsWidgetInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//Verify  blanket Contracts
		Integer NychaBlanketContractsWidgetCountDB = DatabaseUtil.getNychaBlanketContractsCount(year, 'B');
		assertFieldContainsText(" Number of Blanket Agreements", contractsPage.getWidgetNychaCount("Blanket Agreements") , NychaBlanketContractsWidgetCountDB.toString());
	
		//Verify Planned agreements
		Integer NychaPlannedContractsWidgetCountDB = DatabaseUtil.getNychaPlannedContractsCount(year, 'B');
		assertFieldContainsText("Number of Planned Agreements", contractsPage.getWidgetTotalCount("Planned Agreements") , NychaPlannedContractsWidgetCountDB.toString());
		
		//Verify Purchase orders
		
		Integer NychaPurchaseOrdersWidgetCountDB = DatabaseUtil.getNychaPurchaseOrderContractsCount(year, 'B');
		assertFieldContainsText("Number of Purchase Orders", contractsPage.getWidgetNychaCount("Purchase Orders") , NychaPurchaseOrdersWidgetCountDB.toString());
			
		//Verify  Vendors
		Integer NychaVendorsWidgetCountDB = DatabaseUtil.getNychaContractsVendorsCount(year, 'B');
		assertFieldContainsText("Number of Vendors", contractsPage.getWidgetNychaCount("Vendors") , NychaVendorsWidgetCountDB.toString());
			
		//Verify award method
		Integer NychaAwardMethodsWidgetCountDB = DatabaseUtil.getNychaContractsAwardMethodsCount(year, 'B');
		assertFieldContainsText("Number of Award Methods", contractsPage.getWidgetNychaCount("Award Methods") , NychaAwardMethodsWidgetCountDB.toString());
		
		//Verify Nycha Departments
		Integer NychaDepartmentsWidgetCountDB = DatabaseUtil.getNychaContractsDepartmentsCount(year, 'B');
		assertFieldContainsText("Number of Departments", contractsPage.getWidgetNychaCount("Departments") , NychaDepartmentsWidgetCountDB.toString());
				
		//Verify responsibility centers
		Integer NychaResponsibilityCentersWidgetCountDB = DatabaseUtil.getNychaContractsRespCenterCount(year, 'B');
		assertFieldHasText("Number of Responsibility Centers", contractsPage.getWidgetNychaCount("Responsibility Centers") , NychaResponsibilityCentersWidgetCountDB.toString());
	
		//Verify industry
		Integer NychaContractsIndustryWidgetCountDB = DatabaseUtil.getNychaContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", contractsPage.getWidgetNychaCount1("Contracts by Industries") , NychaContractsIndustryWidgetCountDB.toString());
		
		//Verify contract by size
		Integer NychaContractsSizeWidgetCountDB = DatabaseUtil.getNychaContractsCount(year, 'B');
		assertFieldContainsText("Number of Contracts", contractsPage.getWidgetNychaCount1("Contracts by Size") , NychaContractsSizeWidgetCountDB.toString());
	
	
		//Verify blanket modifications
		 
		Integer NychaBlanketModContractsWidgetCountDB = DatabaseUtil.getNychaBlanketModContractsCount(year, 'B');
		assertFieldContainsText("Number of Blanket Agreement Modifications", contractsPage.getWidgetNychaCount("Blanket Agreement Modifications") , NychaBlanketModContractsWidgetCountDB.toString());
		
		//Verify Planned modifications agreements
		Integer NychaPlannedModContractsWidgetCountDB = DatabaseUtil.getNychaPlannedModContractsCount(year, 'B');
		assertFieldContainsText("Number of Planned Agreement Modifications", contractsPage.getWidgetNychaCount("Planned Agreement Modifications") , NychaPlannedModContractsWidgetCountDB.toString());
		
	}
		
	
	@And("^the System displays Nycha Contracts Widgets Detailed Information for \"([^\"]*)\"$")
	public void validateNychaContractsWidgetDetailsInformation(String yearSelected) throws Exception {
		int year = ExecutionContext.getJsonData().get(yearSelected).getAsInt();
		
		//String NychaContractsAmount =  DatabaseUtil.getNychaContractsDetailsAmount(year,'B');
		
		//Verify Nycha blanket Widget Details
		contractsPage.navigateToWidgetDetails("Blanket Agreements");
		Integer NychaBlanketWidgetDetailsCountFromDB = DatabaseUtil.getNychaBlanketContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Blanket Agreements Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaBlanketWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Blanket Agreements Widget Details Title",contractsPage.getWidgetDetailTitle(), "Blanket Agreements Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Checks Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
		contractsPage.navigateToContractsPage();
			
		
		//Verify Nycha Planned Widget Details
		contractsPage.navigateToWidgetDetails("Planned Agreement");
		Integer NychaPlannedContractsWidgetDeetailsCountFromDB = DatabaseUtil.getNychaPlannedContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Planned Agreement Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaPlannedContractsWidgetDeetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Planned Agreement Widget Details Title", contractsPage.getWidgetDetailTitle(), "Agencies Nycha Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Planned Agreement Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
		contractsPage.navigateToContractsPage();
		/*
		//Verify purchase orders
		contractsPage.navigateToWidgetDetails("Purchase Orders");
		Integer NychaPOContractsWidgetDetailsCountFromDB = DatabaseUtil.getNychaPOContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Purchase Orders Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaPOContractsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Purchase Orders Widget Details Title", contractsPage.getWidgetDetailTitle(), "Purchase Orders Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Purchase Orders Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
		contractsPage.navigateToContractsPage();
		*/
		//Verify Departments
		contractsPage.navigateToWidgetDetails("Departments");
		Integer NychaContractsDepartmentsWidgetDetailsCountFromDB = DatabaseUtil.getNychaContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Departments Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaContractsDepartmentsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Departments Widget Details Title", contractsPage.getWidgetDetailTitle(), "Departments Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Departments Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
		contractsPage.navigateToContractsPage();
		
		//Verify Vendors
		contractsPage.navigateToWidgetDetails("Vendors");
		Integer NychaContractsVendorsWidgetDetailsCountFromDB = DatabaseUtil.getNychaContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Vendors Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaContractsVendorsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Vendors Widget Details Title", contractsPage.getWidgetDetailTitle(), "Vendors Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Vendors Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
		contractsPage.navigateToContractsPage();
		
		//Verify Award Methods
		contractsPage.navigateToWidgetDetails("Award Methods");
		Integer NychaContractsAwardMethodsWidgetDetailsCountFromDB = DatabaseUtil.getNychaContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Award Methods Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaContractsAwardMethodsWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Award Methods Widget Details Title", contractsPage.getWidgetDetailTitle(), "Award Methods Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Award Methods Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
		softAssertion.assertAll();
		contractsPage.navigateToContractsPage();
		
		//Verify Responsibility Centers
		contractsPage.navigateToWidgetDetails("Responsibility Centers");
		Integer NychaContractsRespCentersWidgetDetailsCountFromDB = DatabaseUtil.getNychaContractsDetailsCount(year,'B');
		assertFieldContainsText("Nycha Contracts Responsibility Centers Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaContractsRespCentersWidgetDetailsCountFromDB.toString());
		assertFieldHasText("Nycha Contracts Responsibility Centers Widget Details Title", contractsPage.getWidgetDetailTitle(), "Responsibility Centers Contracts Transactions");
		//assertFieldContainsText("Nycha Contracts Responsibility Centers Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
	
		contractsPage.navigateToContractsPage();
		
     //Verify Nycha blanket modifications Widget Details
         contractsPage.navigateToWidgetDetails("Blanket Agreement Modifications");
         Integer NychaBlaModWidgetDetailsCountFromDB = DatabaseUtil.getNychaBlanketModContractsDetailsCount(year,'B');
         assertFieldContainsText("Nycha Contracts Blanket Agreement Modifications Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaBlaModWidgetDetailsCountFromDB.toString());
         assertFieldHasText("Nycha Contracts Blanket Agreement Modifications Widget Details Title", contractsPage.getWidgetDetailTitle(), "Blanket Agreement Modifications Contracts Transactions");
        // assertFieldContainsText("Nycha Contracts Blanket Agreements Modifications Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
         contractsPage.navigateToContractsPage();

       //Verify Nycha Planned Widget modifications Details
       contractsPage.navigateToWidgetDetails("Planned Agreement Modifications");
       Integer NychaPlannedModWidgetDetailsCountFromDB = DatabaseUtil.getNychaPlannedModContractsDetailsCount(year,'B');
       assertFieldContainsText("Nycha Contracts Planned Agreement Modifications Widget Detail Transaction Count", contractsPage.getTotalCountForWidgetDetails() , NychaPlannedModWidgetDetailsCountFromDB.toString());
       assertFieldHasText("Nycha Contracts Planned Agreement Modifications Widget Details Title", contractsPage.getWidgetDetailTitle(), "Planned Agreement Modifications Contracts Transactions");
       //assertFieldContainsText("Nycha Contracts Planned Agreements Modifications Widget Detail Transaction Amount", ContractsPage.getTransactionAmount() , NychaContractsAmount);
       contractsPage.navigateToContractsPage();
   	softAssertion.assertAll();
}
}


