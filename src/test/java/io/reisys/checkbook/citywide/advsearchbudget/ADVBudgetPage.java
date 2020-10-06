package io.reisys.checkbook.citywide.advsearchbudget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class ADVBudgetPage extends CheckbookBasePageObject {	
	
	private static final By BUDGET_TAB = By.xpath("//div[@class='top-navigation-left']//span[text()='Budget']/..");
		
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-budget-submit\"]");
	
	
	 public void navigateToSpendingPage() {
		findElement(BUDGET_TAB).click();
	}
	
	public String getSpendingAmount() {
		String budgetAmount = findElement(BUDGET_TAB).getText();
		return budgetAmount.substring(budgetAmount.indexOf("$"));
	}
	
	public void navigateToAdvancedSearchfromBudgetPage() {
		findElement(Advanced_Search).click();
	}
	public void navigateToAdvancedSearchBudgetSubmit() {
		findElement(Advanced_Search_Submit).click();
	}		

	
	public String getTotalCountForAdvancedSearchCitywideBudgetDetails() {
		return findElement(By.id("table_277_info")).getText().substring(18, 27);
			//	substring(indexOf("f") + 1, indexOf("e"));
		//return findElement(By.id("table_277_info")).getText().substring(indexOf("f") + 1, indexOf("e"));
	}
	public String getAdvancedSearchCitywideBudgetDetailsTitle() {
		return findElement(By.className("contract-title")).getText();
	}
	
}
