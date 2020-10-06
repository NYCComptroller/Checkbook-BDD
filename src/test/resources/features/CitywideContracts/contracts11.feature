@Contracts
Feature: Checkbook Contracts Tab
  As a user I want to go to Contracts Tab so that I can view the different Contracts Details

  Background: 
    Given I navigate to Contracts Page
    When I select "Selected Year" for getting data

  @ActiveExpenseContracts
  Scenario: Navigate to Total Contracts Tab and Validate Total Contracts Widget Data
  	And I navigate to "Total" Contracts sub tab
    Then the System displays Total Contracts Information for "Selected Year"
    And the System displays Total Contracts Widgets for "Selected Year"
    And the System displays Total Contracts Widgets Detailed Information for "Selected Year"
    
  @payrollContracts
  Scenario: Navigate to Payroll Contracts Tab and Validate Payroll Contracts Widget Data
    And I navigate to "Payroll" Contracts sub tab
    Then the System displays Payroll Contracts Information for "Selected Year"
    And the System displays Payroll Contracts Widgets for "Selected Year"
    And the System displays Payroll Contracts Widgets Detailed Information for "Selected Year"
    
  @capitalContracts
  Scenario: Navigate to Capital Contracts Tab and Validate Capital Contracts Widget Data
    And I navigate to "Capital" Contracts sub tab
    Then the System displays Capital Contracts Information for "Selected Year"
    And the System displays Capital Contracts Widgets for "Selected Year"
    And the System displays Capital Contracts Widgets Detailed Information for "Selected Year"
    
  @contractContracts
  Scenario: Navigate to Contract Contracts Tab and Validate Contract Contracts Widget Data
    And I navigate to "Contract" Contracts sub tab
    Then the System displays Contract Contracts Information for "Selected Year"
    And the System displays Contract Contracts Widgets for "Selected Year"
    And the System displays Contract Contracts Widgets Detailed Information for "Selected Year"

  @trustAndAgencyContracts
  Scenario: Navigate to Trust & Agency Contracts Tab and Validate Trust & Agency Contracts Widget Data
    And I navigate to "Trust & Agency" Contracts sub tab
    Then the System displays Trust and Agency Contracts Information for "Selected Year"
    And the System displays Trust and Agency Contracts Widgets for "Selected Year"
    And the System displays Trust and Agency Contracts Widgets Detailed Information for "Selected Year"

    
  @otherContracts
  Scenario: Navigate to Other Contracts Tab and Validate Other Contracts Widget Data
    And I navigate to "Other" Contracts sub tab
    Then the System displays Other Contracts Information for "Selected Year"
    And the System displays Other Contracts Widgets for "Selected Year"
	  And the System displays Other Contracts Widgets Detailed Information for "Selected Year"
    