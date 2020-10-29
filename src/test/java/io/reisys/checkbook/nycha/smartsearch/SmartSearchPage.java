package io.reisys.checkbook.nycha.smartsearch;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class SmartSearchPage extends CheckbookBasePageObject {	
	
	
	private static final By otherGovtAgenciesBy = By.cssSelector("#agency-list-other > .agency-list-open");
	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 //public static final By Smart_search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Payroll_Submit");
	 public static final By Smart_search_Submit = By.xpath("//*[@id=\"edit-submit\"]");
	//private static final By Smart_search_datatype = By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[2]/div/div/div[1]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/div[1]/ul/li[1]/label/div[3]");
	private static final By Smart_search_datatype = By.xpath("//div[@class='name' and text() ='revenue']");
	private static final By Smart_search_year = By.xpath("//div[@class='filter-title' ]//span[text() ='By Fiscal Year']");
	private static final By Smart_search_select_year = By.xpath("//div[@class='name' and text() ='2020']");
	public void navigateToOGEPage() {
		findElement(otherGovtAgenciesBy).click();
	}
	public void navigateToNYCHAPage() {
		findElement(otherGovtAgenciesOptionBy).click();
	}
	
	 public void navigateToSmartsearchPage() {
		findElement(Smart_search_Submit).click();
	}
	
	 public void navigateToSmartsearchRevenue() {
			findElement(Smart_search_datatype).click();
			findElement(Smart_search_year).click();
			findElement(Smart_search_select_year).click();
		}
	
	public String getTotalCountForNychaSmartSearch() {
		return findElement(By.id("smart-search-transactions")).getText();
	}
	public String getSmartsearchNychaTitle() {
		return findElement(By.className("breadcrumb")).getText();
	}
	
	
}
