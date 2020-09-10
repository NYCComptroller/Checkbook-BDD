@AdvancedSearch
Feature: Checkbook Advanced Search Nycha Revenue Tab
  As a user I want to go to Nycha Revenue Tab so that I can view the Nycha Revenue Details

  Background: 
    Given I navigate to Advanced Search Revenue OGE Page
    When I click on Submit
   
    
    @AdvancedSearchNychaRevenue
   Scenario: Navigate to  Nycha Revenue Advanced search and verify default Transactions   
   Then the System displays Nycha Revenue Transactions 
       