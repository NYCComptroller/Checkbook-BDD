@payroll 
Feature: Checkbook Nycha Payroll Tab
  As a user I want to go to Nycha Payroll Tab so that I can view the Nycha Payroll Details

  Background: 
    Given I navigate to OGE Page
    When I select Nycha from  OGE drop down
    When I select "Selected Year" for getting data
    
    @NychaPayroll 
  Scenario: Navigate to  Nycha Payroll Tab and Validate Nycha Payroll  Widget Data
    Then the System displays Nycha Payroll  Widget and Visualization titles for "Selected Year"
   And the System displays  Nycha Payroll  Widget counts and Top nav amount for "Selected Year"
   And the System displays  Nycha Payroll  Widgets Details Information for "Selected Year"
    

    