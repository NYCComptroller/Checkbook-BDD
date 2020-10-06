@AdvancedSearch
Feature: Checkbook Datafeeds NYCEDC Spending Tab
  As a user I want to go to NYCEDC Spending Tab so that I can view the NYCEDC Spending Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Spending
     
    
    @DataFeedsNYCEDCSpending
   Scenario: Navigate to  NYCEDC Spending Data Feeds and verify default Transactions   
   Then I navigate to NYCEDC Spending
   And I submit the form
   And the System displays NYCEDC Spending Transactions 
       