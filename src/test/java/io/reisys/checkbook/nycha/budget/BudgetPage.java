package io.reisys.checkbook.nycha.budget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


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
	public String getWidgetTotalCount2(String widgetOption)
	{
		//String widgetOptionCount = findElement(By.xpath("//span[contains(text(), widgetOption)]")).getText();
		
		String widgetOptionCount=  findElement(By.id("node-widget-expense_categories_percent_difference_2_view")).getText();
		
			return widgetOptionCount.substring(widgetOptionCount.indexOf(":"),widgetOptionCount.indexOf(":")+6).trim();
		
	}
	
	public String getWidgetTotalCount1(String widgetOption)
	{
		//String widgetOptionCount = findElement(By.xpath("//span[contains(text(), widgetOption)]")).getText();
		
		//String widgetOptionCount=  findElement(By.id("node-widget-expense_categories_percent_difference_2_view")).getText();
		//String widgetOptionCount1 = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Number')]"));
		String widgetOptionCount=  findElement(By.xpath("//h2/span[contains(text(), widgetOption)]")).getText();
		//html/body/h2/text()
		//System.out.println("Vin is " + vele.getText());
		
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
	
	public String getTotalCountForTitlesWidgetDetails() {
		return findElement(By.id("table_1046_info")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-spending-amount")).getText();
	}
	
	
	//String widgetOptionCount = findElement(By.xpath("//*[@id=\"node-widget-annual_salaries_agency_view\"]/div[1]/div[1]/h2")).getText();
		//	+ "[contains(text(), 'Number of \" + widgetOption +\"')]")).getText();

	//WebElement element = WebDriver.findElement(By.xpath(".//div[@class='cf']/h1/a"));
	//String text = element.getText();
	//*[@id="node-widget-annual_salaries_agency_view"]/div[1]/div[1]/h2
			//*[@id="node-widget-annual_salaries_agency_view"]/div[1]/div[1]/h2/text()[2]
		//String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();[@id="node-widget-annual_salaries_agency_view"]/div[1]/div[1]/h2/text()[2]
		/*
		public String getWidgetTotalCount(String widgetOption) {
			String widgetOptionCount = findElement(By.xpath("//h2[contains(text(), 'Top " + widgetOption +"')]")).getText();
			return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
		}

		
		public String getWidgetTotalCount(String WidgetOption) {
			//List<WebElement> panelContainers = findElement(By.cssSelector(".bottomContainer > .panel-display > .panel-panel > .inside > .panel-pane"));
			//List<WebElement> panelContainers = Driver.Instance.findElements(By.cssSelector(".bottomContainer > .panel-display > .panel-panel > div > .panel-pane"));
				//for (WebElement panelContainer : panelContainers) {
					WebElement header= findElement(By.tagName("h2"));
					String subTitle = header.getText().substring(0, header.getText().indexOf("Number")-1);
					if(subTitle.equalsIgnoreCase(WidgetOption))
					{	WebElement countContainer = findElement(By.className("contentCount"));
						String numAgencyText = countContainer.getText();
				        String numAgency = numAgencyText.substring(numAgencyText.indexOf(":") + 1).trim();
					
				        return numAgency;}
					return subTitle;
		}
		
			*/		
		
	
}
