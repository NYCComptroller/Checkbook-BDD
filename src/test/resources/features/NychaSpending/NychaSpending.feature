@Nycha
Feature: Checkbook Nycha Sepnding Tab
  As a user I want to go to Nycha Spending Tab so that I can view the Nycha Spending Details

  Background: 
    Given I navigate to OGE Page
    When I select Nycha from  OGE drop down   
    
    @NychaSpending
  Scenario: Navigate to  Nycha Spending Tab and Validate Nycha Spending Widget Data
  Then I navigate to Spending Page
   Then the System displays Nycha Total Spending  Widget and Visualization titles for "Selected Year"
   Then the System displays Nycha Total Spending Widgets count for "Selected Year"
 # And the System displays Nycha Total Spending Widgets Detailed Information for "Selected Year"

  @payrollSpending
  Scenario: Navigate to Payroll Spending Tab and Validate Payroll Spending Widget Data
    Then I navigate to Payroll Spending sub tab
    Then the System displays Payroll Spending Information for "Selected Year"
    Then the System displays Payroll Spending Widgets for "Selected Year"
    And the System displays Payroll Spending Widgets Detailed Information for "Selected Year"
    
    
  @contractSpending
  Scenario: Navigate to Contract Spending Tab and Validate Contract Spending Widget Data
    Then I navigate to Contract Spending sub tab
    Then the System displays Contract Spending Information for "Selected Year"
    Then the System displays Contract Spending Widgets for "Selected Year"
    And the System displays Contract Spending Widgets Detailed Information for "Selected Year"

  @section8Spending
  Scenario: Navigate to Section 8 Spending Tab and Validate Section 8 Spending Widget Data
    Then I navigate to Section 8 Spending sub tab
    Then the System displays Section 8 Spending Information for "Selected Year"
    Then the System displays Section 8 Spending Widgets for "Selected Year"
    And the System displays Section 8 Spending Widgets Detailed Information for "Selected Year"

    
  @otherSpending
  Scenario: Navigate to Other Spending Tab and Validate Other Spending Widget Data
    Then I navigate to Other Spending sub tab
    Then the System displays Other Spending Information for "Selected Year"
    Then the System displays Other Spending Widgets for "Selected Year"
	  And the System displays Other Spending Widgets Detailed Information for "Selected Year"
    


    