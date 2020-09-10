@revenue
Feature: Checkbook Nycha Revenue Tab
  As a user I want to go to Nycha Revenue Tab so that I can view the Nycha Revenue Details

  Background: 
    Given I navigate to OGE Page
    When I select Nycha from  OGE drop down
    When I select Revenue from top navigation
    
    @NychaRevenue 
  Scenario: Navigate to  Nycha Revenue Tab and Validate Nycha Revenue  Widget Data
  Then the System displays Nycha Revenue  Widget and Visualization titles for "Selected Year"
  And the System displays  Nycha Revenue  Widget counts and Top nav amount for "Selected Year"
 And the System displays  Nycha Revenue  Widgets Details Information for "Selected Year"
       