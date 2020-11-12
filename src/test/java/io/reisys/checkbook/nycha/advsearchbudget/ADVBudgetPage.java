package io.reisys.checkbook.nycha.advsearchbudget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
		//return findElement(By.id("table_1034_info")).getText();
			String numCountText = findElement(By.id("table_1034_info")).getText();
		  String numCount1 = numCountText.substring(numCountText.indexOf("f") + 1, numCountText.indexOf("e")).trim();
	        String numCount2 = numCount1.replace(",", "");
	        
	        String numCount3 = numCountText.substring(numCountText.indexOf("m") + 1, numCountText.indexOf("t")).trim();
	        String numCount4 = numCount3.replace(",", "");
	        if (Integer.parseInt(numCount2) <= 200000)
	        return numCount2;
	        else 
	        	return numCount4;
		
	}
	public String getAdvancedSearchOGEBudgetDetailsTitle() {
	 return findElement(By.className("title")).getText();
		
	}
	

}
