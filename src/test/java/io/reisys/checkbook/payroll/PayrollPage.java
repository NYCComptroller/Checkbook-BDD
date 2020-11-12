package io.reisys.checkbook.payroll;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class PayrollPage extends CheckbookBasePageObject {	
	
	private static final By PAYROLL_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Payroll']/..");
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	//	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-payroll-submit\"]");
	
  
//	public void navigateToOGEPage() {
	//	findElement(otherGovtAgenciesBy).click();
//	}

	
	 public void navigateToPayrollPage() {
		findElement(PAYROLL_TAB).click();
	}
	 public void navigateToAdvancedSearchSubmit() {
			findElement(Advanced_Search_Submit).click();
	}
	
	public String getPayrollAmount() {
		String payrollAmount = findElement(PAYROLL_TAB).getText();
		return payrollAmount.substring(payrollAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchPage() {
		findElement(Advanced_Search).click();
	}	

	
	public void navigateToTitlesWidgetDetails(String widgetOption) {
		waitForElement(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]"), 60);
		clickLink(By.xpath("//span[contains(text(), 'Number of " + widgetOption + "')]/../../../../..//a[contains(text(), 'Details')]"));
		waitForElementToDisappear(By.xpath("//img[@title='Loading Data...']"));
		waitForElementToDisappear(By.id("table_886_processing"));
		waitForElement(By.id("table_886_wrapper"), 60);
		
			}

	public String getAdvancedSearchTotalCount() {
		waitForElement(By.id("table_336_info"), 500);
		return findElement(By.id("table_336_info")).getText();
	}
	public String getAdvancedSearchTitle() {
		return findElement(By.className("contract-title")).getText();
	}

	public String getTransactionAmount() {
		return findElement(By.className("total-payroll-amount")).getText();
	}


	public void clickOnYearDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-payroll-year")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnPayFrequencyDropdown(String value) {
		
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-payroll-pay-frequency")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnAgencysDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-payroll-agencies")));
		dropdown.selectByVisibleText(value);
	}

	public void clickOnSalariedTypeRadiobutton(String value) {
		// TODO Auto-generated method stub
		//Select dropdown = new Select(findElement(By.id("edit-payroll-amount-type-1")));
		//dropdown.selectByVisibleText(value).click();
		findElement(By.id("edit-payroll-amount-type-1")).click();
	}



	public void sendValueToTitle(String value) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-employee-name")).sendKeys(value);
	}


	public void enterRangeValueForGrossPayField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-gross-pay-amount-from")).sendKeys(from);
		findElement(By.id("edit-payroll-gross-pay-amount-to")).sendKeys(to);
		
	}
	public void enterRangeValueForGrossPayYTDField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-total-gross-pay-from")).sendKeys(from);
		findElement(By.id("edit-payroll-total-gross-pay-to")).sendKeys(to);
		
	}	

	public void enterRangeValueForBasePayField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-base-salary-from")).sendKeys(from);
		findElement(By.id("edit-payroll-base-salary-to")).sendKeys(to);
		
	}
	public void enterRangeValueForOvertimePayField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-overtime-amount-from")).sendKeys(from);
		findElement(By.id("edit-payroll-overtime-amount-to")).sendKeys(to);
		
	}
	public void enterRangeValueForAmountField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-amount-from")).sendKeys(from);
		findElement(By.id("edit-payroll-amount-to")).sendKeys(to);
		
	}
	public void enterRangeValueForPayDateField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-pay-date-from-datepicker-popup-0")).sendKeys(from);
		findElement(By.id("edit-payroll-pay-date-to-datepicker-popup-0")).sendKeys(to);
		
	}
	public void enterRangeValueForOtherPaymentsField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-payroll-other-payments-from")).sendKeys(from);
		findElement(By.id("edit-payroll-other-payments-to")).sendKeys(to);
		
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
public String getRadiobuttonDefaultValue(String value) {
	// TODO Auto-generated method stub

	return findElement(By.xpath("//label[contains(text(),'All')]")).getText();
	//return findElement(By.id(value)).getText();
	//return findElement(By.id("edit-payroll-amount-type-0")).getText();
}
}
