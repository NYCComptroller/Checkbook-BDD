package io.reisys.checkbook.nycha.advsearchcontracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVContractsPage extends CheckbookBasePageObject {	
	
	private static final By CONTRACTS_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Contracts']/..");
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Contracts_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-contracts-submit--3\"]");
	

	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToContractsPage() {
		findElement(CONTRACTS_TAB).click();
	}
	
	public String getPayrollAmount() {
		String ContractsAmount = findElement(CONTRACTS_TAB).getText();
		return ContractsAmount.substring(ContractsAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromContractsPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchOGEContractsSubmit() {
		findElement(Advanced_Search_Submit).click();
	}
		

	public String getTotalCountForAdvancedSearchOGEContractsDetails() {
		return findElement(By.id("table_979_info")).getText();
	}
	public String getAdvancedSearchOGEContractsDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	

	
}
