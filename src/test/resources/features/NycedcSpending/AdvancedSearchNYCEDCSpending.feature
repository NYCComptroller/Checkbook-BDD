@AdvancedSearch
Feature: Checkbook Advanced Search NYCEDC Spending Tab
  As a user I want to go to NYCEDC Spending Tab so that I can view the NYCEDC Spending Details

  Background: 
    Given I navigate to Advanced Search Spending NYCEDC Page
    When I click on Submit
   
    
 @AdvancedSearchaNYCEDCSpending
   Scenario: Navigate to  NYCEDC Spending Advanced search and verify default Transactions   
   Then the System displays NYCEDC Spending Transactions 