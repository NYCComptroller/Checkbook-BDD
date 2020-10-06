@AdvancedSearch
Feature: Checkbook Datafeeds Citywide Payroll Tab
  As a user I want to go to Citywide Payroll Tab so that I can view the Citywide Payroll Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Payroll
     
    
    @DataFeedsCitywidePayroll
   Scenario: Navigate to  Citywide Payroll Data Feeds and verify default Transactions   
   Then I navigate to Citywide Payroll
   And I submit the form
   And the System displays Citywide Payroll Transactions 
       