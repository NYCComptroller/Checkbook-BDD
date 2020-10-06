@AdvancedSearch
Feature: Checkbook Datafeeds Citywide Budget Tab
  As a user I want to go to Citywide Budget Tab so that I can view the Citywide Budget Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Budget
     
    
    @DataFeedsCitywideBudget
   Scenario: Navigate to  Citywide Budget Data Feeds and verify default Transactions   
   Then I navigate to Citywide Budget
   And I submit the form
   And the System displays Citywide Budget Transactions 
       