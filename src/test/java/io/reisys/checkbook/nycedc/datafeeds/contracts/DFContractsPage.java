package io.reisys.checkbook.nycedc.datafeeds.contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class DFContractsPage extends CheckbookBasePageObject {	
	
	//private static final By DATAFEEDS_TAB = By.xpath("//a[@href ='/datafeeds']");
	//private static final By DATAFEEDS_TAB = By.cssSelector("a[href='/datafeeds']");
	private static final By DATAFEEDS_TAB = By.linkText("Data Feeds");
	public static final By Datafeeds_option = By.xpath("//*[@id=\"edit-type-contracts\"]");
	public static final By Datafeeds_option_submit = By.xpath("//*[@id=\"edit-type-next\"]");
	public static final By Datafeeds_select_citywide = By.xpath("//*[@id=\"edit-datafeeds-contracts-domain-filter-checkbook-oge\"]");
	 public static final By Datafeeds_add_all = By.linkText("Add All");
	 public static final By Datafeeds_default_submit = By.xpath("//*[@id=\"edit-feeds-contract-next\"]");
	 
	 public void navigateToDatafeedsPage() {
		findElement(DATAFEEDS_TAB).click();
	}
		
	
	public void navigateToDatafeedsContractsPage() {
		findElement(Datafeeds_option).click();
		findElement(Datafeeds_option_submit).click();
		//findElement(Datafeeds_select_nycha).click();
		
	}
	public void navigateToDatafeedsNYCEDCContractsPage()
	{
			findElement(Datafeeds_select_citywide).click();
		
	}
	

	public void navigateToDatafeedsNYCEDCContractsSubmit1(){ 
	
		findElement(Datafeeds_add_all).click();
		findElement(Datafeeds_default_submit).click();
	}
		

	
	public String getTotalCountForDatfeeds() {
		return findElement(By.xpath("//div[@class='records-result']")).getText().substring(9, 16);
	}
	

	
}
