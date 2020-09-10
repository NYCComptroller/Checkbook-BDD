package io.reisys.checkbook.nycha.contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ContractsPage extends CheckbookBasePageObject {	
	
	private static final By CONTRACTS_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Contracts']/..");
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
		 	
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToContractsPage() {
		findElement(CONTRACTS_TAB).click();
	}
	
	public String getContractsAmount() {
		String payrollAmount = findElement(CONTRACTS_TAB).getText();
		return payrollAmount.substring(payrollAmount.indexOf("$"));
	}
	
	public String getWidgetNychaCount(String widgetOption) {
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	public String getWidgetNychaCount1(String widgetOption) {
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of Contracts')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
	}
	
	public String getWidgetTotalCount(String widgetOption) {
		String widgetOptionCount = findElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption +"')]")).getText();
		return widgetOptionCount.substring(widgetOptionCount.indexOf(":") + 1).trim();
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

	public void navigateToWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_979_processing"));
		waitForElement(By.id("table_979_wrapper"), 60);
	}
	
	public void navigateToTitlesWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_979_processing"));
		waitForElement(By.id("table_979_wrapper"), 60);
	}
	
	public String getTotalCountForWidgetDetails() {
		return findElement(By.id("table_979_info")).getText();
	}
	
	public String getTotalCountForTitlesWidgetDetails() {
		return findElement(By.id("table_979_info")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-payroll-amount")).getText();
	}
	
}
