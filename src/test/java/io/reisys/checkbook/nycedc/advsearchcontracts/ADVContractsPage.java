package io.reisys.checkbook.nycedc.advsearchcontracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVContractsPage extends CheckbookBasePageObject {	
	
	private static final By CONTRACTS_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Contracts']/..");
		private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-contracts-submit--2\"]");
	

	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCEDCPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToContractsPage() {
		findElement(CONTRACTS_TAB).click();
	}
	
	public String getContractsAmount() {
		String spendingAmount = findElement(CONTRACTS_TAB).getText();
		return spendingAmount.substring(spendingAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromContractsPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchNYCEDCContractsSubmit() {
		findElement(Advanced_Search_Submit).click();
	}
		

	
	public String getTotalCountForAdvancedSearchNYCEDCContractsDetails() {
		return findElement(By.id("table_634_info")).getText();
	}
	public String getAdvancedSearchNYCEDCContractsDetailsTitle() {
		//return findElement(By.className("contract_title")).getText();
		return findElement(By.xpath("//h2[@class='contract-title']")).getText();
	}
	
}
