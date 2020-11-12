package io.reisys.checkbook.smoke;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.google.gson.JsonArray;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import io.reisys.checkbook.bdd.cucumber.ExecutionContext;
import io.reisys.checkbook.home.NYCheckbookPage;
import io.reisys.checkbook.utilities.CommonUtility;
import io.reisys.checkbook.utilities.DatabaseUtil;

public class SmokeSteps extends CheckbookBaseScenarioSteps {
	
	NYCheckbookPage homePage;
	SmokePage SmokePage;
	
	@Given("^I navigate to Checkbook home Page$")
	public void navigateToAdvancedSearchRevenueFormPage() {
		    homePage.open();
		   	   	
			}
@Then("^the System displays top menu items$")
public void primaryMenuItemsExists() {
	 SmokePage.verifyPrimaryMenuItemsExists();
	 SmokePage.verifyToolsMenuItemsExists();
}

@Then("the System displays Tools sub menu titles$")
public void clickOnToolsSubMenuDropdowns() {
	//value = ExecutionContext.getJsonData().get(value).getAsString();
    SmokePage.clickOnMWBEAgencyDropdown();
    assertFieldHasText("MWBE ResourcePage Title",SmokePage.getTitle(), "M/WBE Agency Summary");
    assertEquals(SmokePage.getTitle(), "M/WBE Agency Summary");
    SmokePage.clickOnTrendsDropdown();
    assertFieldHasText("Trends default link Title",SmokePage.getTitle(), "Featured Trends");
    assertEquals(SmokePage.getTitle(), "FEATURED TRENDS");
    
}

@Then("the System displays Trends sub menu titles$")
public void clickOnTrendsSubMenuDropdowns() {
	//value = ExecutionContext.getJsonData().get(value).getAsString();
      
   SmokePage.SubmenuTrends();
    SmokePage.financialsubmenuSelection();
    SmokePage.revenueCapacitySelection();
   SmokePage.debtCapacitySelection();
    SmokePage.demographicSelection();
    SmokePage.operationalSelection();
    }

@Then("the System displays Data Feeds menu title$")
public void clickOnDatafeedsSubMenuDropdowns() {	    
         SmokePage.DataFeeds(); 
         assertFieldHasText("Data Feeds title",SmokePage.getTitle(), "Data Feeds");
         assertEquals(SmokePage.getTitle(), "Data Feeds");
}
   @Then("the System displays Resources sub menu titles$")
   public void clickOnResourcesSubMenuDropdowns() {
   	   SmokePage.MWBEResourcesPagesSelector();
        SmokePage.CheckbookResources();
       assertFieldHasText("Resources title",SmokePage.getTitle(), "Resources");
       assertEquals(SmokePage.getTitle(), "Resources");
   }
      @Then("the System displays Help sub menu titles$")
      public void clickOnHelpSubMenuDropdowns() {
      	  SmokePage.helpsubmenu();
      	 //assertFieldHasText("Site Navigation & Glossary Title",SmokePage.getTitle(), "Site Navigation & Glossary");
         //assertEquals(SmokePage.getTitle(), "Site Navigation & Glossary");
      }
         @Then("the System displays New Features menu title$")
         public void clickOnNewFeaturesSubMenuDropdowns() {
        SmokePage.CheckbookNewfeatures(); 
       //assertFieldHasText("New Features title",SmokePage.getTitle(), "New Features");
      // assertEquals(SmokePage.getTitle(), "New Features");
      
      //  try {
		//	assertEquals("title matches",SmokePage.getTitle(), "New Features");
		//} 
		//catch(AssertionError ae) {
		//}
}

}
