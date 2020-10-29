package io.reisys.checkbook.citywide.advsearchrevenue;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVRevenuePage extends CheckbookBasePageObject {	
	
	private static final By REVENUE_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Revenue']/..");
		
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-revenue-submit\"]");
	
	
	 public void navigateToRevenuePage() {
		findElement(REVENUE_TAB).click();
	}
	
	public String getRevenueAmount() {
		String revenueAmount = findElement(REVENUE_TAB).getText();
		return revenueAmount.substring(revenueAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromRevenuePage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchRevenueSubmit() {
		findElement(Advanced_Search_Submit).click();
	}		

	
	public String getTotalCountForAdvancedSearchCitywideRevenueDetails() {
		return findElement(By.id("table_280_info")).getText();
		
	}
	public String getAdvancedSearchCitywideRevenueDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
}
