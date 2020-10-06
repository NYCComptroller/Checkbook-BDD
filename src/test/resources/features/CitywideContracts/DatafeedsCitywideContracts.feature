@AdvancedSearch
Feature: Checkbook Datafeeds Citywide Contracts Tab
  As a user I want to go to Citywide Contracts Tab so that I can view the Citywide Contracts Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Contracts
     
    
    @DataFeedsCitywideContracts
   Scenario: Navigate to  Citywide Contracts Data Feeds and verify default Transactions   
   Then I navigate to Citywide Contracts
   And I submit the form
   And the System displays Citywide Contracts Transactions 
       