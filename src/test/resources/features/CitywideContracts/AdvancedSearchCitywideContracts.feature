@AdvancedSearch
Feature: Checkbook Advanced Search Citywide Contracts Tab
  As a user I want to go to Citywide Contracts Tab so that I can view the Contracts Spending Details

  Background: 
    Given I navigate to Advanced Search Citywide Contracts Page
    When I click on Submit
   
    
 @AdvancedSearchaCitywideContracts
   Scenario: Navigate to  Citywide Contracts Advanced search and verify default Transactions   
   Then the System displays Citywide Contracts Transactions 