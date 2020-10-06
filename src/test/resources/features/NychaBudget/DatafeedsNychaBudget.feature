@AdvancedSearch
Feature: Checkbook Datafeeds Nycha Budget Tab
  As a user I want to go to Nycha Budget Tab so that I can view the Nycha Budget Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Datafeeds Budget
     
    
    @DataFeedsNychaBudget
   Scenario: Navigate to  Nycha Budget Data Feeds and verify default Transactions   
   Then I navigate to NYCHA Budget
   And I submit the form
   And the System displays Nycha Budget Transactions 
       