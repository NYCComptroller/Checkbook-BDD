@Budget
Feature: Checkbook Budget Tab
  As a user I want to go to Citywide  Budget Tab so that I can view the different Budget Details

  Background: 
    Given I navigate to Citywide Budget Page
    When I select "Selected Year" for getting data

  @CitywideBudget
  Scenario: Navigate to Citywide  Budget Tab and Validate Total Budget Widget Data
  
    Then the System displays Citywide Budget Widgets and Visualizations titles for "Selected Year"
    And the System displays Citywide  Budget Widgets counts and Top nav amount for "Selected Year"
    And the System displays  Citywide Budget  Widgets Details Information for "Selected Year"
    

    