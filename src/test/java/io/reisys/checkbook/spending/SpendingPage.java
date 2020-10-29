package io.reisys.checkbook.spending;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class SpendingPage extends CheckbookBasePageObject {	
	
	private static final By SPENDING_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Spending']/..");
	//span[@class='nav-title'][contains(text(),'Spending')]
	
	public void navigateToSpendingPage() {
		findElement(SPENDING_TAB).click();
	}
	
	public String getSpendingAmount() {
		String spendingAmount = findElement(SPENDING_TAB).getText();
		return spendingAmount.substring(spendingAmount.indexOf("$"));
	}
		
	public String getBottomNavSpendingAmount() {
		String bottomNavSpendingAmount = findElement(By.cssSelector(".nyc_totals_links .active > .positioning > a .dollars")).getText();
	    return bottomNavSpendingAmount.substring(bottomNavSpendingAmount.indexOf("$"));
	}
	
	public String getWidgetTotalCount(String widgetOption) {
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	
	public void navigateToSubTabType(String subTabType) {
		clickLink(By.xpath("//div[@class='positioning']/a[contains(text(), '" + subTabType + "')]"));
		waitForRenderedElementsToDisappear(By.id("table_checks_view_processing"));
	}
	
	public String getCurrentSelectedSubTabType() {
		return findElement(By.xpath("//td[contains(@class, 'active')]/div[@class='positioning']/a")).getText();
	}
	
	public void navigateToWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_706_processing"));
		waitForElement(By.id("table_706_wrapper"), 60);
	}
	
	public String getTotalCountForWidgetDetails() {
		return findElement(By.id("table_706_info")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-spending-amount")).getText();
	}
	
}
