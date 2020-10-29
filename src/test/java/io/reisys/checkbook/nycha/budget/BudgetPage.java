package io.reisys.checkbook.nycha.budget;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class BudgetPage extends CheckbookBasePageObject {	
	
	private static final By BUDGET_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Budget']/..");
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 

	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToBudgetPage() {
		findElement(BUDGET_TAB).click();
	}
	
	public String getBudgetAmount() {
		String budgetAmount = findElement(BUDGET_TAB).getText();
		return budgetAmount.substring(budgetAmount.indexOf("$"));
	}
			
	public String getWidgetTotalCount(String widgetOption)
	{
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCountId(String widgetOption)
	{
		//String widgetOptionCount = findElement(By.xpath("//span[contains(text(), widgetOption)]")).getText();
		
		String widgetOptionCount=  findElement(By.id("node-widget-expense_categories_percent_difference_2_view")).getText();
		
			return widgetOptionCount.substring(widgetOptionCount.indexOf(":"),widgetOptionCount.indexOf(":")+6).trim();
		
	}
	
	public String getWidgetTotalCount1(String widgetOption)
	{
		//String widgetOptionCount = findElement(By.xpath("//span[contains(text(), widgetOption)]")).getText();
		//String widgetOptionCount=  findElement(By.id("node-widget-expense_categories_percent_difference_2_view")).getText();
		//String widgetOptionCount=  findElement(By.xpath("//h2//span[2][contains(text(), widgetOption)]")).getText();
		String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-1']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount2(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-2']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount3(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-3']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount5(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-5']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount6(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-6']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount8(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-8']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount9(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-9']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount11(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-11']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount12(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-12']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount14(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-14']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetTotalCount15(String widgetOption)
	{
				String widgetOptionCount=  findElement(By.xpath("//div[@class='panel-pane pane-custom pane-15']//h2[1]")).getText();
				return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	
	
	

	public void navigateToWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_1034_processing"));
		waitForElement(By.id("table_1034_wrapper"), 60);
	}
	
	public void navigateToExpCatModWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_1046_processing"));
		waitForElement(By.id("table_1046_wrapper"), 60);
	}
	
	public String getTotalCountForWidgetDetails() {
		return findElement(By.id("table_1034_info")).getText();
	}
	
	public String getTotalCountForModWidgetDetails() {
		return findElement(By.id("table_1046_info")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-spending-amount")).getText();
	}
	
	
	}
