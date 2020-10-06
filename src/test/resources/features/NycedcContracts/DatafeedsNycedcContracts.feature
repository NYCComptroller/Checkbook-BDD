@AdvancedSearch
Feature: Checkbook Datafeeds NYCEDC Contracts Tab
  As a user I want to go to NYCEDC Contracts Tab so that I can view the NYCEDC Contracts Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Contracts
     
    
    @DataFeedsNYCEDCContracts
   Scenario: Navigate to  NYCEDC Contracts Data Feeds and verify default Transactions   
   Then I navigate to NYCEDC Contracts
   And I submit the form
   And the System displays NYCEDC Contracts Transactions 
       