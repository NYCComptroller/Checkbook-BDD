@budget
Feature: Checkbook Nycha Budget Tab
  As a user I want to go to Nycha Budget Tab so that I can view the Nycha Budget Details

  Background: 
    Given I navigate to OGE Page
    When I select Nycha from  OGE drop down
    When I select Budget from top navigation
    
    @NychaBudget 
  Scenario: Navigate to  Nycha Budget Tab and Validate Nycha Budget  Widget Data
  
   Then the System displays Nycha Budget  Widget and Visualization titles for "Selected Year"
   And the System displays  Nycha Budget  Widget counts and Top nav amount for "Selected Year"
   And the System displays  Nycha Budget  Widgets Details Information for "Selected Year"
    

    