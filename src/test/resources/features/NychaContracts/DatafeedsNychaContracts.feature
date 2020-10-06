@AdvancedSearch
Feature: Checkbook Datafeeds Nycha Contracts Tab
  As a user I want to go to Nycha Contracts Tab so that I can view the Nycha Contracts Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Datfeeds Contracts
     
    
    @DataFeedsNychaContracts
   Scenario: Navigate to  Nycha Contracts Data Feeds and verify default Transactions   
   Then I navigate to NYCHA Contracts
   And I submit the form
   And the System displays Nycha Contracts Transactions 
       