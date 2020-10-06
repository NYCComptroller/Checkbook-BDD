@SmartSearch 
Feature: Checkbook Smart Search NYCEDC
  As a user I want to go to Smart Search NYCEDC so that I can search and get the NYCEDC transactions

  Background: 
    Given I navigate to NYCEDC Smart Search Page
    When I click on Submit
          
    @SmartSearchNycedc    
   Scenario: Navigate to Smart Search NYCEDC results Page and verify default Transactions  
    
   Then the System displays NYCEDC Transactions
   Then the System displays NYCEDC Spending Transactions
     