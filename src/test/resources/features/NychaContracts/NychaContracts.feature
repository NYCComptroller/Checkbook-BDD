@Nycha
Feature: Checkbook Nycha Contracts Tab
  As a user I want to go to Nycha Contracts Tab so that I can view the Nycha Contracts Details

  Background: 
    Given I navigate to OGE Page
    When I select Nycha from  OGE drop down   
    
    @NychaContracts 
  Scenario: Navigate to  Nycha Contracts Tab and Validate Nycha Contracts  Widget Data
   Then the System displays Nycha Contracts  Widget and Visualization titles for "Selected Year"
   Then the System displays contracts Widgets count for "Selected Year"
  And the System displays Nycha Contracts Widgets Detailed Information for "Selected Year"


    