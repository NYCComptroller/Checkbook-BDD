package io.reisys.checkbook.nycha.advsearchrevenue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVRevenuePage extends CheckbookBasePageObject {	
	
	private static final By REVENUE_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Revenue']/..");
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Revenue_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-revenue-submit--2\"]");
	

	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToRevenuePage() {
		findElement(REVENUE_TAB).click();
	}
	
	public String getRevenueAmount() {
		String RevenueAmount = findElement(REVENUE_TAB).getText();
		return RevenueAmount.substring(RevenueAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromRevenuePage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchOGERevenueSubmit() {
		findElement(Advanced_Search_Submit).click();
	}
		

	public String getTotalCountForAdvancedSearchOGERevenueDetails() {
		return findElement(By.id("table_1051_info")).getText();
	}
	public String getAdvancedSearchOGERevenueDetailsTitle() {
		return findElement(By.className("title")).getText();
	}
	

	
}
