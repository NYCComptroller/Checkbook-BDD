@SmartSearch 
Feature: Checkbook Smart Search Nycha
  As a user I want to go to Smart Search Nycha so that I can search and get the Nycha transactions

  Background: 
    Given I navigate to Nycha Smart Search Page
    When I click on Submit
          
    @SmartSearchNycha    
   Scenario: Navigate to Smart Search Nycha results Page and verify default Transactions  
    
   Then the System displays Nycha Transactions
   Then the System displays Nycha Revenue Transactions
    