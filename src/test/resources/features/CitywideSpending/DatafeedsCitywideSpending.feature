@AdvancedSearch
Feature: Checkbook Datafeeds Citywide Spending Tab
  As a user I want to go to Citywide Spending Tab so that I can view the Citywide Spending Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Spending
     
    
    @DataFeedsCitywideSpending
   Scenario: Navigate to  Citywide Spending Data Feeds and verify default Transactions   
   Then I navigate to Citywide Spending
   And I submit the form
   And the System displays Citywide Spending Transactions 
       