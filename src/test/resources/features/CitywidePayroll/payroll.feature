@spending
Feature: Checkbook Citywide Payroll Tab
  As a user I want to go to Citywide Payroll Tab so that I can view the different Payroll Details

  Background: 
    Given I navigate to Citywide Payroll Page
    When I select "Selected Year" for getting data

  @CitywidePayroll
  Scenario: Navigate to  Citywide Payroll Tab and Validate Citywide Payroll Widget Data
    Then the System displays Citywide Payroll Widget and Visualization titles for "Selected Year"
    And the System displays  Citywide Payroll  Widget counts and Top nav amount for "Selected Year"
    And the System displays  Citywide Payroll  Widgets Details Information for "Selected Year"
    
  