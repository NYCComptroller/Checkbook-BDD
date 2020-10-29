package io.reisys.checkbook.nycha.advsearchbudget;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVBudgetPage extends CheckbookBasePageObject {	
	
	private static final By BUDGET_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Budget']/..");
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Budget_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-budget-submit--2\"]");
	

	
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
	
	public void navigateToAdvancedSearchfromBudgetPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchOGESubmit() {
		findElement(Advanced_Search_Submit).click();
	}
		
	
		public String getTotalCountForAdvancedSearchOGEBudgetDetails() {
		return findElement(By.id("table_1034_info")).getText();
	}
	public String getAdvancedSearchOGEBudgetDetailsTitle() {
	 return findElement(By.className("title")).getText().substring(0,7);
		
	}
	

}
