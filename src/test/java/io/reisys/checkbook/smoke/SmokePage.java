package io.reisys.checkbook.smoke;

import static org.junit.Assert.assertTrue;
import io.reisys.checkbook.bdd.common.CheckbookBaseScenarioSteps;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import io.reisys.checkbook.bdd.common.CheckbookBasePageObject;

public class SmokePage extends CheckbookBasePageObject {	
	

	 //public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY ECONOMIC DEVELOPMENT CORPORATION");
	 public static final By otherGovtAgenciesOptionBy = By.linkText("NEW YORK CITY HOUSING AUTHORITY");
	
	 public static final By Advanced_Search = By.linkText("Advanced Search");
	// public static final By Advanced_Search_Submit = By.linkText("Revenue_Submit");
	 public static final By Advanced_Search_Submit = By.xpath("//*[@id=\"edit-revenue-submit--2\"]");
	 
	 public static final By h1title = By.xpath("//*[@id=\"page-title\"]");
	    // Primary Menu Options
	    public static final By home = By.cssSelector(".menu-230");
	   // public static final By tools = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Tools')]");
	    public static final By tools = By.xpath("//span[contains(text(),'Tools')]");
	    public static final By dataFeeds = By.linkText("Data Feeds");
	    public static final By resources = By.linkText("Resources");
	    public static final By help = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Help')]");
	    public static final By newfeatures = By.xpath("//a[contains(text(),'New Features')]");
	    
	    // Tools
	    public static final By trends = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Trends')]");
	   // public static final By MWBEAgencySummary = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'M/WBE Agency Summary')]");
	    public static final By MWBEAgencySummary = By.xpath("//a[contains(text(),'M/WBE Agency Summary')]");

	 // Tools>Trends
	    public static final By featuredTrends = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Featured Trends')]");
	    public static final By allTrends = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'All Trends')]");
	    public static final By financial = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Financial')]");
	    public static final By revenueCapacity = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Revenue Capacity')]");
	    public static final By debtCapacity = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Debt Capacity')]");
	    public static final By demographic = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Demographic')]");
	    public static final By operational = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'Operational')]");

	    // Tools>Trends>Financial
	    public static final By changesInNetAssets = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Changes in Net Assets')]");
	    public static final By fundBalances = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Fund Balances-Governmental Funds')]");
	    public static final By changesInFundBalances = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Changes in Fund Balances')]");
	    public static final By generalFundRevenues = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'General Fund Revenues and Other Financing Sources')]");
	    public static final By generalFundExpenditures = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'General Fund Expenditures and Other Financing Uses')]");
	    public static final By capitalProjectsFundAidRevenues = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Capital Projects Fund Aid Revenues')]");
	    public static final By NYCEducationalConstructionFund = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'New York City Educational Construction Fund')]");

	    // Tools>Trends>RevenueCapacity
	    public static final By assessedValueAndEstimatedActualValue = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Assessed Value and Estimated Actual Value of Taxable Property')]");
	    public static final By propTaxRates = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Property Tax Rates')]");
	    public static final By propTaxLeviesAndCollections = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Property Tax Levies and Collections')]");
	    public static final By assessedValuationAndTaxRateByClass = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Assessed Valuation and Tax Rate by Class')]");
	    public static final By collectionsCancellations = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Collections, Cancellations, Abatements and Other Discounts as a Percent of Tax Levy')]");
	    public static final By uncollectedParkingViolationFines = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Uncollected Parking Violation Fines')]");
	    public static final By hudsonYardsInfrastructure = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Hudson Yards Infrastructure Corporation')]");

	    // Tools>Trends>DebtCapacity
	    public static final By ratiosOfOutstandingDebt = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Ratios of Outstanding Debt by Type')]");
	    public static final By ratiosOfCityGeneralBondedDebt = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Ratios of City General Bonded Debt Payable')]");
	    public static final By legalDebtMarginInfo = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Legal Debt Margin Information')]");
	    public static final By pledgedRevenueCoverageNYC = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Pledged-Revenue Coverage NYC Transitional Finance Authority')]");

	    // Tools>Trends>Demographic
	    public static final By population = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Population')]");
	    public static final By personalIncome = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Personal Income')]");
	    public static final By nonagriculturalWageSalaryEmployment = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Nonagricultural Wage Salary Employment')]");
	    public static final By personsReceivingPublicAssistance = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Persons Receiving Public Assistance')]");
	    public static final By employmentStatus = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Employment Status of the Resident Population')]");

	    // Tools>Trends>Operational
	    public static final By numberOfFullTimeCityEmployees = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Number of Full Time City Employees')]");
	    public static final By capitalAssetsStats = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Capital Assets Statistics by Function/Program')]");

	    // Resources
	    private static final By MWBEResources = By.xpath("//*[@id=\"nice-menu-1\"]//span[contains(text(),'MWBE Resources')]");

	    // Resources>MWBE Resources
	    public static final By MWBEReports= By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'M/WBE Reports')]");
		public static final By agencygrade = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Agency Grade')]");
		public static final By MWBEInteractiveMaps= By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'M/WBE Interactive Maps')]");		  
	    public static final By directoryOfCertifiedBusiness = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Directory of Certified Businesses')]");
	    public static final By becomeACertifiedMWBEVendor = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Become a Certified M/WBE Vendor')]");
	    public static final By sellingToTheGovt = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Selling to the Government')]");
	    public static final By helpForBusiness = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Help for Business')]");
	    public static final By contractingOpportunities = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Contracting Opportunities')]");

	    // Help
	    private static final By siteNavigationAndGlossary = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Site Navigation & Glossary')]");
	    private static final By instructionalVideos = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Instructional Videos')]");
	    private static final By FAQ = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'FAQ')]");
	    private static final By askAQuestion = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Ask a Question')]");
	    private static final By reportAProblem = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Report a Problem')]");
	    private static final By shareAnIdea = By.xpath("//*[@id=\"nice-menu-1\"]//a[contains(text(),'Share an Idea')]");


	
	 public void navigateToTopMenu() {
			findElement(home).click();
		}
	 public  boolean isPresent(By by) {
		    return findAllElements(by).size() > 0;
		 }
	    public boolean isAt(String pageTitle) {        

	        return findElement(h1title).getText().equals(pageTitle);
	    }
	    
		public String getTitle() {
			 return findElement(By.className("title")).getText();
				
			}
		 
	 public void verifyPrimaryMenuItemsExists() {
	        assertTrue(isPresent(home));
	        assertTrue(isPresent(tools));
	        assertTrue(isPresent(dataFeeds));
	        assertTrue(isPresent(resources));
	        assertTrue(isPresent(help));
	        assertTrue(isPresent(newfeatures));
	        
	    }
	 public void verifyToolsMenuItemsExists() {
	        assertTrue(isPresent(trends));
	        assertTrue(isPresent(MWBEAgencySummary));	              
	    }
	 
	
	    public void verifyMWBEAgencySummary() {
	    	findElement(MWBEAgencySummary).click();
	    	assertTrue(isAt("M/WBE Agency Summary"));
	    	
	    	    }

	    private  void dropdownSelector(By optionOne, By optionTwo) {
	  
	        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
	        WebElement link = getDriver().findElement(optionOne);
	        link.click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTwo));

	        link.findElement(optionTwo).click();
	    }
	    
	    private void dropdownSelector(By optionOne, By optionTwo, By optionThree) {
	       
	        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
	        WebElement link = getDriver().findElement(optionOne);

	        // Hover and hold
	        Actions action = new Actions(getDriver());
	        action.moveToElement(getDriver().findElement(optionOne)).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTwo));

	        action.moveToElement(getDriver().findElement(optionTwo)).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionThree));

	        link.findElement(optionThree).click();
	    }

	    private  void dropdownSelector(By optionOne, By optionTwo, By optionThree, By optionFour) {
	     
	        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
	        WebElement link = getDriver().findElement(optionOne);
	        link.click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTwo));

	        // Hover and hold
	        Actions action = new Actions(getDriver());
	        action.moveToElement(getDriver().findElement(optionTwo)).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionThree));

	        action.moveToElement(getDriver().findElement(optionThree)).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionFour));

	        link.findElement(optionFour).click();
	    }


	    public  void clickOnMWBEAgencyDropdown() {
	    	
	    	dropdownSelector(tools, MWBEAgencySummary);	     
	       System.out.println("Done Mouse hover on 'tools' from Menu");
	   	    	System.out.println("Done Mouse hover on 'mwbe agency' from Menu");
	   	 	assertTrue(isAt("M/WBE Agency Summary"));
		}
   public void clickOnTrendsDropdown() {	   
	    	dropdownSelector(tools, trends);	     
	       System.out.println("Done Mouse hover on 'tools' from Menu");
	      	System.out.println("Done Mouse hover on 'Trends' from Menu");	  	    	
		}
 
           public void SubmenuTrends() {
               dropdownSelector(tools, trends, featuredTrends);
               dropdownSelector(tools, trends, allTrends);         
               dropdownSelector(tools, trends, financial);
               dropdownSelector(tools, trends, revenueCapacity);
               dropdownSelector(tools, trends, debtCapacity);
               dropdownSelector(tools, trends, demographic);
               dropdownSelector(tools, trends, operational);
           }
          
                   public void financialsubmenuSelection() {
                   dropdownSelector(tools, trends, financial,changesInNetAssets );
                   dropdownSelector(tools, trends, financial,fundBalances );
                   dropdownSelector(tools, trends, financial,changesInFundBalances );
                   dropdownSelector(tools, trends, financial,generalFundRevenues );
                   dropdownSelector(tools, trends, financial,generalFundExpenditures );
                   dropdownSelector(tools, trends, financial,capitalProjectsFundAidRevenues );
                   dropdownSelector(tools, trends, financial,NYCEducationalConstructionFund );
                                  }  
              
               public  void revenueCapacitySelection() {
                   dropdownSelector(tools, trends, revenueCapacity, assessedValueAndEstimatedActualValue);
                   dropdownSelector(tools, trends, revenueCapacity, propTaxRates);
                   dropdownSelector(tools, trends, revenueCapacity, propTaxLeviesAndCollections);
                   dropdownSelector(tools, trends, revenueCapacity, assessedValuationAndTaxRateByClass);
                   dropdownSelector(tools, trends, revenueCapacity, collectionsCancellations);
                   dropdownSelector(tools, trends, revenueCapacity, uncollectedParkingViolationFines);
                   dropdownSelector(tools, trends, revenueCapacity, hudsonYardsInfrastructure);
               
               }    
               public  void debtCapacitySelection() {
                   dropdownSelector(tools, trends, debtCapacity, pledgedRevenueCoverageNYC);
                   dropdownSelector(tools, trends, debtCapacity, legalDebtMarginInfo);
                   dropdownSelector(tools, trends, debtCapacity, ratiosOfCityGeneralBondedDebt);
                   dropdownSelector(tools, trends, debtCapacity, ratiosOfOutstandingDebt);
           
               }      

               public  void demographicSelection() {
            	   dropdownSelector(tools, trends, demographic, personalIncome);
            	   dropdownSelector(tools, trends, demographic, population);
                   dropdownSelector(tools, trends, demographic, nonagriculturalWageSalaryEmployment);
                   dropdownSelector(tools, trends, demographic, personsReceivingPublicAssistance);
                   dropdownSelector(tools, trends, demographic, employmentStatus);
               } 

               public  void operationalSelection() {
                   dropdownSelector(tools, trends, operational, numberOfFullTimeCityEmployees);
                   dropdownSelector(tools, trends, operational, capitalAssetsStats);
         
               }
    
       public  void MWBEAgencySummary() {
           dropdownSelector(tools, MWBEAgencySummary);
       }
       public  void MWBEResourcesPagesSelector() {
           dropdownSelectornew(resources, MWBEResources,MWBEReports );
           dropdownSelectornew(resources, MWBEResources,agencygrade );
           dropdownSelectornew(resources, MWBEResources,MWBEInteractiveMaps );
           dropdownSelectornew(resources, MWBEResources,directoryOfCertifiedBusiness );
           dropdownSelectornew(resources, MWBEResources,becomeACertifiedMWBEVendor );
           dropdownSelectornew(resources, MWBEResources,sellingToTheGovt );
           dropdownSelectornew(resources, MWBEResources,helpForBusiness );
           dropdownSelectornew(resources, MWBEResources,contractingOpportunities );
       }
   
   public  void DataFeeds() {
       findElement(dataFeeds).click();   }

 
      public void CheckbookResources() {
    	     findElement(resources).click();
      }
      public void CheckbookNewfeatures() {	    
 	     
 	     
 	 // Store the current window handle
 	    String winHandleBefore = getDriver().getWindowHandle();

 	    // Perform the click operation that opens new window
 	   findElement(newfeatures).click();
 	    // Switch to new window opened
 	    for(String winHandle : getDriver().getWindowHandles()){
 	        getDriver().switchTo().window(winHandle);
 	    }
 	    // Perform the actions on new window
 	   assertEquals("title matches",getTitle(), "New Features");
 	      // assertEquals(getTitle(), "New Features");
 	    // Close the new window, if that window no more required
 	    getDriver().close();

 	    // Switch back to original browser (first window)
 	    getDriver().switchTo().window(winHandleBefore);

 	    // Continue with original browser (first window)
 	     
   }


     
       public  void helpsubmenu() {
           dropdownSelector(help, siteNavigationAndGlossary);
           dropdownSelector(help, instructionalVideos);
           dropdownSelector(help, FAQ);
       }

  
       public  void AskAQuestion() {
           dropdownSelector(help, askAQuestion);
       }

       public  void ReportAProblem() {
           dropdownSelector(help, reportAProblem);
       }

       public  void ShareAnIdea() {
           dropdownSelector(help, shareAnIdea);
       }
       
       private void dropdownSelectornew(By optionOne, By optionTwo, By optionThree) {
	       
	        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
	        WebElement link = getDriver().findElement(optionOne);

	        // Hover and hold
	        Actions action = new Actions(getDriver());
	        action.moveToElement(getDriver().findElement(optionOne)).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTwo));

	        action.moveToElement(getDriver().findElement(optionTwo)).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(optionThree));
	        link.findElement(optionThree).click();
	        //link.findElement(optionThree).sendKeys(Keys.CONTROL+"t");
	        //Keys.chord(Keys.CONTROL,Keys.RETURN); 
	        getDriver().navigate().back();
	       // JavascriptExecutor js = (JavascriptExecutor)getDriver();    
	        // ((JavascriptExecutor)link.findElement(optionThree)).executeScript("window.open()");
	  	
	    }
       
     
 
}	
 

