@spending
Feature: Checkbook Spending Tab
  As a user I want to go to Spending Tab so that I can view the different Spending Details

  Background: 
    Given I navigate to Spending Page
    When I select "Selected Year" for getting Spending data

  @totalSpending
  Scenario: Navigate to Total Spending Tab and Validate Total Spending Widget Data
  	And I navigate to "Total" Spending sub tab
    Then the System displays Total Spending Information for "Selected Year"
    And the System displays Total Spending Widgets for "Selected Year"
    
  @payrollSpending
  Scenario: Navigate to Payroll Spending Tab and Validate Payroll Spending Widget Data
    And I navigate to "Payroll" Spending sub tab
    Then the System displays Payroll Spending Information for "Selected Year"
    And the System displays Payroll Spending Widgets for "Selected Year"
    
  @capitalSpending
  Scenario: Navigate to Capital Spending Tab and Validate Capital Spending Widget Data
    And I navigate to "Capital" Spending sub tab
    Then the System displays Capital Spending Information for "Selected Year"
    And the System displays Capital Spending Widgets for "Selected Year"
    
  @contractSpending
  Scenario: Navigate to Contract Spending Tab and Validate Contract Spending Widget Data
    And I navigate to "Contract" Spending sub tab
    Then the System displays Contract Spending Information for "Selected Year"
    And the System displays Contract Spending Widgets for "Selected Year"

  @trustAndAgencySpending
  Scenario: Navigate to Trust & Agency Spending Tab and Validate Trust & Agency Spending Widget Data
    And I navigate to "Trust & Agency" Spending sub tab
    Then the System displays Trust and Agency Spending Information for "Selected Year"
    And the System displays Trust and Agency Spending Widgets for "Selected Year"

    
  @otherSpending
  Scenario: Navigate to Other Spending Tab and Validate Other Spending Widget Data
    And I navigate to "Other" Spending sub tab
    Then the System displays Other Spending Information for "Selected Year"
    And the System displays Other Spending Widgets for "Selected Year"

    