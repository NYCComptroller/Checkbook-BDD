package io.reisys.checkbook.citywide.datafeeds.budget;

import org.openqa.selenium.By;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class DFBudgetPage extends CheckbookBasePageObject {	
	
	//private static final By DATAFEEDS_TAB = By.xpath("//a[@href ='/datafeeds']");
	//private static final By DATAFEEDS_TAB = By.cssSelector("a[href='/datafeeds']");
	private static final By DATAFEEDS_TAB = By.linkText("Data Feeds");
	public static final By Datafeeds_option = By.xpath("//*[@id=\"edit-type-budget\"]");
	public static final By Datafeeds_option_submit = By.xpath("//*[@id=\"edit-type-next\"]");
	public static final By Datafeeds_select_citywide = By.xpath("//*[@id=\"edit-datafeeds-budget-domain-filter-checkbook\"]");
	 public static final By Datafeeds_add_all = By.linkText("Add All");
	 public static final By Datafeeds_default_submit = By.xpath("//*[@id=\"edit-feeds-budget-next\"]");
	 
	 public void navigateToDatafeedsPage() {
		findElement(DATAFEEDS_TAB).click();
	}
		
	
	public void navigateToDatafeedsBudgetPage() {
		findElement(Datafeeds_option).click();
		findElement(Datafeeds_option_submit).click();
		//findElement(Datafeeds_select_nycha).click();
		
	}
	public void navigateToDatafeedscitywideBudgetPage()
	{
			findElement(Datafeeds_select_citywide).click();
		
	}
	

	public void navigateToDatafeedsCitywideBudgetSubmit1(){ 
	
		findElement(Datafeeds_add_all).click();
		findElement(Datafeeds_default_submit).click();
	}
		

	
	public String getTotalCountForDatfeeds() {
		return findElement(By.xpath("//div[@class='records-result']")).getText().substring(9, 17);
	}
	

	
}
