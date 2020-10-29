package io.reisys.checkbook.nycha.datafeeds.payroll;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class PayrollPage extends CheckbookBasePageObject {	
	
	//private static final By DATAFEEDS_TAB = By.xpath("//a[@href ='/datafeeds']");
	//private static final By DATAFEEDS_TAB = By.cssSelector("a[href='/datafeeds']");
	private static final By DATAFEEDS_TAB = By.linkText("Data Feeds");
	public static final By Datafeeds_option = By.xpath("//*[@id=\"edit-type-payroll\"]");
	public static final By Datafeeds_option_submit = By.xpath("//*[@id=\"edit-type-next\"]");
	public static final By Datafeeds_select_nycha = By.xpath("//*[@id=\"edit-datafeeds-payroll-domain-filter-checkbook-nycha\"]");
	 public static final By Datafeeds_add_all = By.linkText("Add All");
	 public static final By Datafeeds_default_submit = By.xpath("//*[@id=\"edit-feeds-payroll-next\"]");
	 
	 public void navigateToDatafeedsPage() {
		findElement(DATAFEEDS_TAB).click();
	}
		
	
	public void navigateToDatafeedsPayrollPage() {
		findElement(Datafeeds_option).click();
		findElement(Datafeeds_option_submit).click();
		findElement(Datafeeds_select_nycha).click();
		
	}
	public void navigateToDatafeedsNychaPayrollPage()
	{
			findElement(Datafeeds_select_nycha).click();
		
	}
	

	public void navigateToDatafeedsNychaPayrollSubmit1(){ 
	
		findElement(Datafeeds_add_all).click();
		findElement(Datafeeds_default_submit).click();
	}
		

	
	public String getTotalCountForDatfeeds() {
		return findElement(By.id("edit-count")).getText();
	}
	

	
}
