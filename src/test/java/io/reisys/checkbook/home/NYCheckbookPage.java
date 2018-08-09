package io.reisys.checkbook.home;

import org.openqa.selenium.WebElement;

import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import net.serenitybdd.core.annotations.findby.By;

public class NYCheckbookPage extends CheckbookBasePageObject {

	public void selectYear(String year) {
		String yearToSelect = ExecutionContext.getJsonData().get(year).getAsString();
		findElement(By.cssSelector("#year_list_chosen > .chosen-single")).click();
		//TODO add check to see if the year is already selected
		
		WebElement dropdown = findElement(By.cssSelector("#year_list_chosen > .chosen-drop > .chosen-results"));
		dropdown.findElement(By.xpath("//li[contains(text(), 'FY " + yearToSelect +"')]")).click();
	}
	


}
