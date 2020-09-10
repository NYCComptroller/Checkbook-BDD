@AdvancedSearch
Feature: Checkbook Datafeeds Nycha Payroll Tab
  As a user I want to go to Nycha Payroll Tab so that I can view the Nycha Payroll Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Datfeeds Payroll
     
    
    @DataFeedsNychaPayroll
   Scenario: Navigate to  Nycha Payroll Data Feeds and verify default Transactions   
   Then I navigate to NYCHA payroll
   And I submit the form
   And the System displays Nycha Payroll Transactions 
       