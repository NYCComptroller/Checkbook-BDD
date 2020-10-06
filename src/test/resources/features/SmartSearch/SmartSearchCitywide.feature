@SmartSearch 
Feature: Checkbook Smart Search Citywide
  As a user I want to go to Smart Search Citywide so that I can search and get the Citywide transactions

  Background: 
    Given I navigate to Citywide Smart Search Page
    When I click on Submit
          
    @SmartSearchCitywide   
   Scenario: Navigate to Smart Search Citywide results Page and verify default Transactions  
    
   Then the System displays Citywide Transactions
   Then the System displays Citywide Revenue Transactions