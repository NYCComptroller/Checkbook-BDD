@AdvancedSearch
Feature: Checkbook Advanced Search NYCEDC Contracts Tab
  As a user I want to go to NYCEDC Contracts Tab so that I can view the NYCEDC Contracts Details

  Background: 
    Given I navigate to Advanced Search Contracts NYCEDC Page
    When I click on Submit
   
    
 @AdvancedSearchNYCEDCContracts
   Scenario: Navigate to  NYCEDC Contracts Advanced search and verify default Transactions   
   Then the System displays NYCEDC Contracts Transactions 