@AdvancedSearch
Feature: Checkbook Advanced Search Nycha Payroll Tab
  As a user I want to go to Nycha Payroll Tab so that I can view the Nycha Payroll Details

  Background: 
    Given I navigate to Advanced Search Payroll OGE Page
    When I click on Submit
   
    
    @AdvancedSearchaNychaPayroll
   Scenario: Navigate to  Nycha Payroll Advanced search and verify default Transactions   
   Then the System displays Nycha Payroll Transactions 
       