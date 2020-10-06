@AdvancedSearch
Feature: Checkbook Datafeeds Citywide Revenue Tab
  As a user I want to go to Citywide Revenue Tab so that I can view the Citywide Revenue Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Revenue
     
    
    @DataFeedsCitywideRevenue
   Scenario: Navigate to  Citywide Revenue Data Feeds and verify default Transactions   
   Then I navigate to Citywide Revenue
   And I submit the form
   And the System displays Citywide Revenue Transactions 
       