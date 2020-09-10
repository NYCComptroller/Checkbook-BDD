@AdvancedSearch
Feature: Checkbook Advanced Search Nycha Contracts Tab
  As a user I want to go to Nycha Contracts Tab so that I can view the Nycha Contracts Details

  Background: 
    Given I navigate to Advanced Search Contracts OGE Page
    When I click on Submit
   
    
    @AdvancedSearchNychaContracts    
   Scenario: Navigate to  Nycha Contracts Advanced search and verify default Transactions   
   Then the System displays Nycha Contracts Transactions 
       