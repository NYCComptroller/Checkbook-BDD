@AdvancedSearch
Feature: Checkbook Datafeeds Nycha Revenue Tab
  As a user I want to go to Nycha Revenue Tab so that I can view the Nycha Revenue Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Datafeeds Revenue
     
    
    @DataFeedsNychaRevenue
   Scenario: Navigate to  Nycha Revenue Data Feeds and verify default Transactions   
   Then I navigate to NYCHA Revenue
   And I submit the form
   And the System displays Nycha Revenue Transactions 
       