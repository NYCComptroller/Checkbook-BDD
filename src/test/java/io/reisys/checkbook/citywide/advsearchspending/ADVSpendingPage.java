package io.reisys.checkbook.citywide.advsearchspending;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVSpendingPage extends CheckbookBasePageObject {	
	
	private static final By SPENDING_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Spending']/..");
		
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-spending-submit\"]");
	

	
	
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
		

	
	public String getTotalCountForAdvancedSearchCitywideSpendingDetails() {
		return findElement(By.id("table_1012_info")).getText();
	}
	public String getAdvancedSearchCitywideSpendingDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
}
