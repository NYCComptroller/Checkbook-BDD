@AdvancedSearch
Feature: Checkbook Advanced Search Citywide Payroll Tab
  As a user I want to go to Citywide Payroll Tab so that I can view the Payroll Details

  Background: 
    Given I navigate to Advanced Search Citywide Payroll Page
    When I click on Submit
   
    
 @AdvancedSearchaCitywidePayroll
   Scenario: Navigate to  Citywide Payroll Advanced search and verify default Transactions   
   Then the System displays Citywide Payroll Transactions 