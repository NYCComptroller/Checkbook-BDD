package io.reisys.checkbook.budget;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class BudgetPage extends CheckbookBasePageObject {	
	
	private static final By BUDGET_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Budget']/..");
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	//	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-budget-submit\"]");
	
  
	//public void navigateToOGEPage() {
	//	findElement(otherGovtAgenciesBy).click();
	//}

	
	 public void navigateToPayrollPage() {
		findElement(BUDGET_TAB).click();
		
	}
	 public void navigateToAdvancedSearchSubmit() {
			findElement(Advanced_Search_Submit).click();
	}
	
	public String getPayrollAmount() {
		String payrollAmount = findElement(BUDGET_TAB).getText();
		return payrollAmount.substring(payrollAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchPage() {
		findElement(Advanced_Search).click();
	}

	

	public String getTotalCountForAdvancedSearchDetails() {
		return findElement(By.id("table_277_info")).getText();
	}
	public String getAdvancedSearchDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}

	public String getTransactionAmount() {
		return findElement(By.className("total-payroll-amount")).getText();
	}


	public void clickOnBudgetFyDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-budget-year")));
		dropdown.selectByVisibleText(value);
	}
	
	public void clickOnAgencysDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-budget-agency")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnDepartmentDropdown(String value) {
		
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-budget-department")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnExpenseCategoryDropdown(String value) {
		
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-checkbook-budget-expense-category")));
		dropdown.selectByVisibleText(value);
	}


	public void clickOnBudgetCodeDropdown(String value) {
		// TODO Auto-generated method stub
		//Select dropdown = new Select(findElement(By.id("edit-checkbook-budget-budget-code")));
		Select dropdown = new Select(findElement(By.xpath("//span[contains(text(),'Select Budget Code')]")));
		
		dropdown.selectByVisibleText(value);
	}
	public void clickOnBudgetNameDropdown(String value) {
		// TODO Auto-generated method stub
		//Select dropdown = new Select(findElement(By.id("edit-checkbook-budget-budget-name")));
		Select dropdown = new Select(findElement(By.xpath("//span[contains(text(),'Select Budget Name')]")));
		dropdown.selectByVisibleText(value);
	}

	public void sendValueToBudgetCode(String value) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-budget-budget-name")).sendKeys(value);
	}


	public void sendValueToBudgetName(String value) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-budget-budget-code")).sendKeys(value);
	}


	public void enterRangeValueForAdoptedField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-budget-adopted-budget-from")).sendKeys(from);
		findElement(By.id("edit-checkbook-budget-adopted-budget-to")).sendKeys(to);
		
	}

	public void enterRangeValueForModifiedField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-budget-modified-from")).sendKeys(from);
		findElement(By.id("edit-checkbook-budget-modified-to")).sendKeys(to);
	}
	public void enterRangeValueForPreEncumberedField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-checkbook-budget-pre-encumbered-from")).sendKeys(from);
	findElement(By.id("edit-checkbook-budget-pre-encumbered-to")).sendKeys(to);
	}

	public void enterRangeValueForEncumberedField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-checkbook-budget-encumbered-from")).sendKeys(from);
	findElement(By.id("edit-checkbook-budget-encumbered-to")).sendKeys(to);
	}
	
	public void enterRangeValueForAccruedExpenseField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-checkbook-budget-accrued-expense-from")).sendKeys(from);
		findElement(By.id("edit-checkbook-budget-accrued-expense-to")).sendKeys(to);
	}
	public void enterRangeValueForCashPaymentsField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-checkbook-budget-cash-payments-from")).sendKeys(from);
	findElement(By.id("edit-checkbook-budget-cash-payments-to")).sendKeys(to);
	}

	public void enterRangeValueForPostAdjustmentsField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-checkbook-budget-post-adjustments-from")).sendKeys(from);
	findElement(By.id("edit-checkbook-budget-post-adjustments-to")).sendKeys(to);
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
