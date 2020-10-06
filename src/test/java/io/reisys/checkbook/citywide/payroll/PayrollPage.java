package io.reisys.checkbook.citywide.payroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class PayrollPage extends CheckbookBasePageObject {	
	
	private static final By PAYROLL_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Payroll']/..");
	 	
	
	
	 public void navigateToPayrollPage() {
		findElement(PAYROLL_TAB).click();
	}
	
	public String getPayrollAmount() {
		String payrollAmount = findElement(PAYROLL_TAB).getText();
		return payrollAmount.substring(payrollAmount.indexOf("$"));
	}
	
	public String getWidgetTotalCount(String widgetOption) {
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}

		

	public void navigateToWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_317_processing"));
		waitForElement(By.id("table_317_wrapper"), 60);
	}
	
	public void navigateToTitlesWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_886_processing"));
		waitForElement(By.id("table_886_wrapper"), 60);
	}
	
	public String getTotalCountForWidgetDetails() {
		return findElement(By.id("table_317_info")).getText();
	}
	
	public String getTotalCountForTitlesWidgetDetails() {
		return findElement(By.id("table_886_info")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-payroll-amount")).getText();
	}
	
}
