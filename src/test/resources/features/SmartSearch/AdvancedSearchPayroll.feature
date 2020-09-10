@AdvancedSearchPayroll
Feature: Checkbook Advanced search Payroll
  As a user I want to go to Advanced search Payroll Tab so that I can search and get the Payroll transactions

  Background: 
    Given I navigate to Advanced search form Page
    When I select Payroll from  Advanced search
    When I click on Submit
      
    @AdvancedSearchPayroll    
   Scenario: Navigate to   Advanced search Payroll domain form and verify default Transactions  
    
   Then the System displays Payroll Transactions for "Selected Year"    
  
     