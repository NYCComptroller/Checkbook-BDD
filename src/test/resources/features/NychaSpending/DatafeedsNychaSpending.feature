@AdvancedSearch
Feature: Checkbook Datafeeds Nycha Spending Tab
  As a user I want to go to Nycha Spending Tab so that I can view the Nycha Spending Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Datafeeds Spending
     
    
    @DataFeedsNychaSpending
   Scenario: Navigate to  Nycha Spending Data Feeds and verify default Transactions   
   Then I navigate to NYCHA Spending
   And I submit the form
   And the System displays Nycha Spending Transactions 
       