@AdvancedSearch
Feature: Checkbook Advanced Search Citywide Revenue Tab
  As a user I want to go to Citywide Revenue Tab so that I can view the Revenue Details

  Background: 
    Given I navigate to Advanced Search Citywide Revenue Page
    When I click on Submit
   
    
 @AdvancedSearchaCitywideRevenue
   Scenario: Navigate to  Citywide Revenue Advanced search and verify default Transactions   
   Then the System displays Citywide Revenue Transactions 