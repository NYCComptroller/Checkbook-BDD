@AdvancedSearch
Feature: Checkbook Advanced Search Nycha Budget Tab
  As a user I want to go to Advanced search Nycha Budget  so that I can view the Nycha Budget Details

  Background: 
    Given I navigate to Advanced Search Budget NYCHA form Page
    When I click on Submit   
    
    @AdvancedSearchaNychaBudget
   Scenario: Navigate to  Nycha Budget Advanced search and verify default Transactions   
   Then the System displays Nycha Budget Transactions 
       