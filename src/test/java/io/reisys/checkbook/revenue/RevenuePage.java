package io.reisys.checkbook.revenue;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class RevenuePage extends CheckbookBasePageObject {	
	
	private static final By REVENUE_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Revenue']/..");
	 public static final By Advanced_Search = By.linkText("Advanced Search");
		private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-revenue-submit\"]");
	
  
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}

	
	 public void navigateToPayrollPage() {
		findElement(REVENUE_TAB).click();
		
	}
	 public void navigateToAdvancedSearchSubmit() {
			findElement(Advanced_Search_Submit).click();
	}
	
	public String getPayrollAmount() {
		String payrollAmount = findElement(REVENUE_TAB).getText();
		return payrollAmount.substring(payrollAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchPage() {
		findElement(Advanced_Search).click();
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
	public String getTotalCountForAdvancedSearchDetails() {
		return findElement(By.id("table_280_info")).getText();
	}
	public String getAdvancedSearchDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getWidgetDetailTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
	public String getTransactionAmount() {
		return findElement(By.className("total-payroll-amount")).getText();
	}


	public void clickOnBudgetFyDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-revenue-budget-fiscal-year")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnFundingClassDropdown(String value) {
		
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-revenue-funding-class")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnAgencysDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-revenue-agency")));
		dropdown.selectByVisibleText(value);
	}


	public void sendValueToRevenueSource(String value) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-revenue-revenue-source")).sendKeys(value);
	}


	public void sendValueToRevenueClass(String value) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-revenue-revenue-class")).sendKeys(value);
	}


	public void enterRangeValueForAdoptedField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-revenue-adopted-budget-from")).sendKeys(from);
		findElement(By.id("edit-checkbook-revenue-adopted-budget-to")).sendKeys(to);
		
	}


	public void enterRangeValueForModifiedField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-revenue-modified-from")).sendKeys(from);
		findElement(By.id("edit-checkbook-revenue-modified-to")).sendKeys(to);
	}


	public void enterRangeValueForRecognizedField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-checkbook-revenue-recognized-from")).sendKeys(from);
	findElement(By.id("edit-checkbook-revenue-recognized-to")).sendKeys(to);
	}
  public String getLabelName(String Xpath) {
	  
	 return findElement(By.xpath("//label[@for=\""+Xpath+"\"]")).getText();
  }




public String getDropDownDefaultValue(String value) {
	// TODO Auto-generated method stub
	Select select = new Select(findElement(By.id(value)));
	String defaultItem =  select.getFirstSelectedOption().getText();
	return defaultItem;
}
}
