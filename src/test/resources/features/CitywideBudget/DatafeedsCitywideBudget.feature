@AdvancedSearch
Feature: Checkbook Datafeeds Citywide Budget Tab
  As a user I want to go to Citywide Budget Tab so that I can view the Citywide Budget Details

  Background: 
    Given I navigate to Data Feeds Page
    When I navigate to Data Feeds Budget
     
    
    @DataFeedsCitywideBudgetDefault
   Scenario: Navigate to  Citywide Budget Data Feeds and verify default Transactions   
   Then I navigate to Citywide Budget
   And I submit the form
   And the System displays Citywide Budget Transactions 
   
    @DataFeedsCitywideBudgetAllFields 
    
      Scenario: Populate All fields in advanced search
       Then I navigate to Citywide Budget
  When click on Budget Fiscal Year drop down for "Selected Year"   
    When click on Agency dropdown and select "Selected Agency"
    When click on Department dropdown and select "Selected Department"
    When click on Expense Category dropdown and select "Selected Expense Category"
   When enter "Selected Budget Code" as value to field budget code
   When I enter adopted field range from "Adopted From" to "Adopted To" 
  When I enter modified field range from "Modified From" to "Modified To" 
  When I enter Pre Encumbered field range from "Pre Encumbered From" to "Pre Encumbered To"
  When I enter Encumbered field range from "Encumbered From" to "Encumbered To"
  When I enter Accrued Expense field range from "Accrued Expense From" to "Accrued Expense To"
  When I enter Cash Payments field range from "Cash Payments From" to "Cash Payments To"
  When I enter Post Adjustments field range from "Post Adjustments From" to "Post Adjustments To"
   And I submit the form
   And the System displays Citywide Budget Transactions    