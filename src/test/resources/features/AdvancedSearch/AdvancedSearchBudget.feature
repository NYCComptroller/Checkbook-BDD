@AdvancedSearch 
Feature: Checkbook Advanced search Budget
  As a user I want to go to Advanced search budget  Tab so that I can search and get the Budget transactions

  Background: 
    Given I navigate to Advanced search form Page
    When I select Budget from  Advanced search
    When I click on Submit
      
    @AdvancedSearchBudget    
   Scenario: Navigate to   Advanced search Budget domain form and verify default Transactions  
    
   Then the System displays Budget Transactions for "Selected Year"      
   And the System displays  Budget Top nav amount for "Selected Year"
     

    