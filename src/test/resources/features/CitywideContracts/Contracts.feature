@Contracts
Feature: Checkbook Contracts Tab
  As a user I want to go to Contracts Tab so that I can view the different Contracts Details

  Background: 
    Given I navigate to Contracts Page
      When I select "Selected Year" for getting data

  @ActiveExpenseContracts
  Scenario: Navigate to "Active Expense Contracts" Tab and Validate Active Expense Contracts Widget Data
    And I navigate to "Active" Expense Contracts sub tab
    Then the System displays Active Expense Contracts Information for "Selected Year" 
    And the System displays Active Expense Contracts Widgets for "Selected Year" 
    
  