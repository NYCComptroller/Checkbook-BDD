package io.reisys.checkbook.citywide.advsearchpayroll;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVPayrollPage extends CheckbookBasePageObject {	
	
	private static final By PAYROLL_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Payroll']/..");
		
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-payroll-submit\"]");
	
	
	 public void navigateToPayrollPage() {
		findElement(PAYROLL_TAB).click();
	}
	
	public String getPayrollAmount() {
		String PayrollAmount = findElement(PAYROLL_TAB).getText();
		return PayrollAmount.substring(PayrollAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromPayrollPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchPayrollSubmit() {
		findElement(Advanced_Search_Submit).click();
	}		

	
	public String getTotalCountForAdvancedSearchCitywidePayrollDetails() {
		return findElement(By.id("table_336_info")).getText();
		
	}
	public String getAdvancedSearchCitywidePayrollDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
}
