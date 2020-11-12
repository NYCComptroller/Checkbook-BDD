package io.reisys.checkbook.citywide.datafeeds.budget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
	
	public void clickOnBudgetFyDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-fiscal-year")));
		dropdown.selectByVisibleText(value);
	}
	
	public void clickOnAgencysDropdown(String value) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-agency")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnDepartmentDropdown(String value) {
		
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-dept")));
		dropdown.selectByVisibleText(value);
	}
	public void clickOnExpenseCategoryDropdown(String value) {
		
		// TODO Auto-generated method stub
		Select dropdown = new Select(findElement(By.id("edit-expense-category")));
		dropdown.selectByVisibleText(value);
	}

	public void sendValueToBudgetCode(String value) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-budget-code")).sendKeys(value);
	}

	public void enterRangeValueForAdoptedField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-adoptedfrom")).sendKeys(from);
		findElement(By.id("edit-adoptedto")).sendKeys(to);
			}

	public void enterRangeValueForModifiedField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-currentfrom")).sendKeys(from);
		findElement(By.id("edit-currentto")).sendKeys(to);
	}
	public void enterRangeValueForPreEncumberedField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-preencumberedfrom")).sendKeys(from);
	findElement(By.id("edit-preencumberedto")).sendKeys(to);
	}

	public void enterRangeValueForEncumberedField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-encumberedfrom")).sendKeys(from);
	findElement(By.id("edit-encumberedto")).sendKeys(to);
	}
	
	public void enterRangeValueForAccruedExpenseField(String from, String to) {
		// TODO Auto-generated method stub
		findElement(By.id("edit-accruedexpensefrom")).sendKeys(from);
		findElement(By.id("edit-accruedexpenseto")).sendKeys(to);
	}
	public void enterRangeValueForCashPaymentsField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-cashfrom")).sendKeys(from);
	findElement(By.id("edit-cashto")).sendKeys(to);
	}

	public void enterRangeValueForPostAdjustmentsField(String from, String to) {
		// TODO Auto-generated method stub
		
	findElement(By.id("edit-postadjustmentsfrom")).sendKeys(from);
	findElement(By.id("edit-postadjustmentsto")).sendKeys(to);
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
