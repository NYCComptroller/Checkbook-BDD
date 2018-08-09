package io.reisys.checkbook.spending;

import org.openqa.selenium.By;

import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class SpendingPage extends CheckbookBasePageObject {	
	
	private static final By SPENDING_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Spending']/..");
	
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
	}
	
	public String getCurrentSelectedSubTabType() {
		return findElement(By.xpath("//td[contains(@class, 'active')]/div[@class='positioning']/a")).getText();
	}
	
}
