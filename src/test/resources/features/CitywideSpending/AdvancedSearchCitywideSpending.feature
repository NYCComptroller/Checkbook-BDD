@AdvancedSearch
Feature: Checkbook Advanced Search Citywide Spending Tab
  As a user I want to go to Citywide Spending Tab so that I can view the Citywide Spending Details

  Background: 
    Given I navigate to Advanced Search Spending Citywide Page
    When I click on Submit
   
    
 @AdvancedSearchaCitywideSpending
   Scenario: Navigate to  Citywide Spending Advanced search and verify default Transactions   
   Then the System displays Citywide Spending Transactions 