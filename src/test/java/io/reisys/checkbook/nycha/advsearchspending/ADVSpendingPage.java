package io.reisys.checkbook.nycha.advsearchspending;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVSpendingPage extends CheckbookBasePageObject {	
	
	private static final By SPENDING_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Spending']/..");
		private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-spending-submit--3\"]");
	

	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToSpendingPage() {
		findElement(SPENDING_TAB).click();
	}
	
	public String getSpendingAmount() {
		String spendingAmount = findElement(SPENDING_TAB).getText();
		return spendingAmount.substring(spendingAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromSpendingPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchOGESpendingSubmit() {
		findElement(Advanced_Search_Submit).click();
	}
		

	
	public String getTotalCountForAdvancedSearchOGESpendingDetails() {
		return findElement(By.id("table_1012_info")).getText();
	}
	public String getAdvancedSearchOGESpendingDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
}
