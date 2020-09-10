package io.reisys.checkbook.bdd.common;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.gson.JsonObject;

import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CheckbookBasePageObject extends PageObject {
	
	private static final int TIMEOUT = 30;

	public void clickElement(WebElement element){
		try{
			element.click();
		}catch (WebDriverException e){
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].click()",element);
		}
	}
	
	public JsonObject getJsonData() {
		return ExecutionContext.getJsonData();
	}
	
	public void waitForElement(By by) {
		withTimeoutOf(TIMEOUT, ChronoUnit.SECONDS).waitForPresenceOf(by);
	}
	
	public void waitForElement(By by, int timeoutSeconds) {
		withTimeoutOf(timeoutSeconds, ChronoUnit.SECONDS).waitForPresenceOf(by);
	}
	
	public void waitForElementToDisappear(By by) {
		if (checkForPresenceOfElementWithoutWaiting(by)) {
			withTimeoutOf(TIMEOUT, ChronoUnit.SECONDS).waitForElementsToDisappear(by);
		}
	}
	
	public WebElement findElement(By by) {
		withTimeoutOf(TIMEOUT, ChronoUnit.SECONDS).waitForPresenceOf(by);
		return find(by);
	}
	
	public List<WebElementFacade> findAllElements(By by) {
		withTimeoutOf(TIMEOUT, ChronoUnit.SECONDS).waitForPresenceOf(by);
		return findAll(by);
	}
 	
	public WebElement findElement(By by, int timeoutSeconds) {
		withTimeoutOf(timeoutSeconds, ChronoUnit.SECONDS).waitForPresenceOf(by);
		return find(by);
	}
	
	public List<WebElementFacade> findAllElements(By by, int timeoutSeconds) {
		withTimeoutOf(timeoutSeconds, ChronoUnit.SECONDS).waitForPresenceOf(by);
		return findAll(by);
	}

    public WebElement elementToBeClickable(By by) {
        withTimeoutOf(TIMEOUT, ChronoUnit.SECONDS).waitFor(ExpectedConditions.elementToBeClickable(by));
        return find(by);
    }

	public void enterValue(By by, String value) {
		element(by).clear();
		element(by).sendKeys(value);
		ExecutionContext.appendToStepData("Input", by.toString(), value); 
	}
	
	public void clickLink(By by) {
		ExecutionContext.appendToStepData("Link", by.toString(), null); 
		findElement(by).click();
	}
	
	public boolean checkForPresenceOfElement(By by) {
		return findElement(by) != null;
	}
	
	public boolean checkForPresenceOfElementWithoutWaiting(By by) {
		return find(by) != null;
	}

	
	//Methods related to getting rows/column data from Table
	public WebElement findTableByCaption(String caption) {
	    return element(By.xpath("//table[caption='" + caption  + "']"));
	}
	
	public WebElement findTableById(String id) {
	    List<WebElement> tables = find(By.tagName("table"));
	    for (WebElement table : tables) {
	    	if (id.equalsIgnoreCase(table.getAttribute("id"))) {
	    		return table;
	    	}
	    }
	    return null;
	}
	
	public int getTableRowCount(WebElement table) {
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
	    return allRows.size() - 1;
	}
	
	public WebElement getNthTableRow(WebElement table, int n) {
	    List<WebElement> allRows = table.findElements(By.tagName("tr"));
	    return (WebElement)allRows.get(n);
	}
	
	public WebElement getRowWithCellValue(WebElement table, int colNo, String cellValue) {
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		for (WebElement row: allRows) {
			WebElement cell = getCellAtIndex(row, colNo);
			if (cell != null && cell.getText().equals(cellValue)) {
				return row;
			}
		}
		
		return null;
	}
	
	public WebElement getCellAtIndex(WebElement row, int colNo) {
		List<WebElement> cells =  row.findElements(By.tagName("td"));
		if (cells != null && cells.size() >=colNo) {
			return cells.get(colNo - 1);
		}
		
		return null;
	}

	public void scrollToBottom() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
		
	public void scrollToTop() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	
	public void scrollUntilElementVisible(WebElementFacade element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	

    public ArrayList<String> getVisualizationTitles() {
		ArrayList<String> titles = new ArrayList<String>();
		List<WebElementFacade> visualizationLinks = findAllElements(By.xpath("//div[contains(@class, 'slider-pager')]/a"));
		for(WebElement visualizationLink: visualizationLinks){
			visualizationLink.click();
			waitForElement(By.xpath("//h2[@class='chart-title']"), 10) ;
			WebElement titleClass = findElement(By.xpath("//h2[@class='chart-title']"));
			if(titleClass.isDisplayed()){
				String title = titleClass.getText();
				titles.add(title);
			}
		}	
		return titles;
	}
    
	public ArrayList<String> getWidgetTitles() {
		ArrayList<String> titles = new ArrayList<String>();
		List<WebElementFacade> titleContainers = findAllElements(By.className("tableHeader"));
		for (WebElement titleContainer : titleContainers) {
			WebElement titleHeaderContainer = titleContainer.findElement(By.cssSelector("h2"));
			titles.add(titleHeaderContainer.getText().substring(0, titleHeaderContainer.getText().indexOf("Number")-1));
		}	
		return titles;
	}
}
