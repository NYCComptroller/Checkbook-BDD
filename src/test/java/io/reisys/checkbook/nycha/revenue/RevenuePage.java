package io.reisys.checkbook.nycha.revenue;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class RevenuePage extends CheckbookBasePageObject {	
	
	private static final By REVENUE_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Revenue']/..");
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 

	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToRevenuePage() {
			waitForElement(By.xpath("//div[@class='top-navigation-left']//span[text()='Revenue']/.."), 60);
		findElement(REVENUE_TAB).click();
	}
	
	public String getRevenueAmount() {
		String REVENUEAmount = findElement(REVENUE_TAB).getText();
		return REVENUEAmount.substring(REVENUEAmount.indexOf("$"));
	}
			
	public String getWidgetTotalCount(String widgetOption)
	{
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	
	public String getWidgetTotalCount1(String widgetOption)
	{
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), widgetOption)]")).getText();
		
			return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
		
	}
	

	public void navigateToWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_1041_processing"));
		waitForElement(By.id("table_1051_wrapper"), 60);
	}
	
	public void navigateToExpCatModWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_1046_processing"));
		waitForElement(By.id("table_1046_wrapper"), 60);
	}
	
	public String getTotalCountForWidgetDetails() {
		return findElement(By.id("table_1051_info")).getText();
	}
	
	public String getTotalCountForTitlesWidgetDetails() {
		return findElement(By.id("table_1051_info")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-spending-amount")).getText();
	}
	
	
	
}
