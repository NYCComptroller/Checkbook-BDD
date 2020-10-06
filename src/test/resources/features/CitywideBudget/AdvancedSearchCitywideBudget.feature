@AdvancedSearch
Feature: Checkbook Advanced Search Citywide Budget Tab
  As a user I want to go to Citywide Budget Tab so that I can view the Budget Details

  Background: 
    Given I navigate to Advanced Search Citywide Budget Page
    When I click on Submit
   
    
 @AdvancedSearchaCitywideBudget
   Scenario: Navigate to  Citywide Budget Advanced search and verify default Transactions   
   Then the System displays Citywide Budget Transactions 