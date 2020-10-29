package io.reisys.checkbook.citywide.advsearchcontracts;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVContractsPage extends CheckbookBasePageObject {	
	
	private static final By CONTRACTS_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Contracts']/..");
		
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-contracts-submit\"]");
	
	
	 public void navigateToSpendingPage() {
		findElement(CONTRACTS_TAB).click();
	}
	
	public String getSpendingAmount() {
		String spendingAmount = findElement(CONTRACTS_TAB).getText();
		return spendingAmount.substring(spendingAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromContractsPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchContractsSubmit() {
		findElement(Advanced_Search_Submit).click();
	}		

	
	public String getTotalCountForAdvancedSearchCitywideContractsDetails() {
		return findElement(By.id("table_939_info")).getText();
		
	}
	public String getAdvancedSearchCitywideContractsDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
}
