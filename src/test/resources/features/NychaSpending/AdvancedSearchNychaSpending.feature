@AdvancedSearch
Feature: Checkbook Advanced Search Nycha Spending Tab
  As a user I want to go to Nycha Spending Tab so that I can view the Nycha Spending Details

  Background: 
    Given I navigate to Advanced Search Spending OGE Page
    When I click on Submit
   
    
 @AdvancedSearchaNychaSpending
   Scenario: Navigate to  Nycha Spending Advanced search and verify default Transactions   
   Then the System displays Nycha Spending Transactions 
   