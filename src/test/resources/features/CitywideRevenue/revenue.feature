@revenue
Feature: Checkbook Revenue Tab
  As a user I want to go to Citywide Revenue Tab so that I can view the different Revenue Details

  Background: 
    Given I navigate to Citywide Revenue Page
    When I select "Selected Year" for getting data

  @CitywideRevenue
  Scenario: Navigate to Citywide Revenue Tab and Validate Revenue Widget Data
    Then the System displays Citywide Revenue  Widget and Visualization titles for "Selected Year"
    And the System displays  Citywide Revenue  Widget counts and Top nav amount for "Selected Year"
    And the System displays  Citywide Revenue  Widgets Details Information for "Selected Year"    