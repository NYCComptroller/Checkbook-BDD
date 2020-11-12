@AdvancedSearchBudget
Feature: Checkbook Advanced Search Citywide Budget Tab
  As a user I want to go to Citywide Budget Tab so that I can view the Citywide Budget Details

  Background: 
    Given I navigate to budget Advanced search form Page
   
   @PopulateBudgetAdvancedSearch
   Scenario: Populate Budget data and click on dropdowns
    When click on Budget Fiscal Year drop down for "Selected Year"   
    When click on Agency dropdown and select "Selected Agency"
    When click on Department dropdown and select "Selected Department"
    When click on Expense Category dropdown and select "Selected Expense Category"
    When click on Budget Code dropdown and select "Selected Budget Code"
    When click on Budget Name dropdown and select "Selected Budget Name"
    Then I click on Submit button for budget domain
    Then the System displays Budget Transactions for "Selected Year"  
    
  @PopulateTextFields
  Scenario: Populate text fields in advanced search
  When enter "Budget Code" as value to field budget code 
  When enter "Budget Name" as value to field budget name
  Then I click on Submit button for budget domain
  Then the System displays Budget Transactions for "Selected Year"  
  
  @PopulateRangeFields
  Scenario: Populate range fields in advanced search
  When I enter adopted field range from "Adopted From" to "Adopted To" 
  When I enter modified field range from "Modified From" to "Modified To" 
  When I enter Pre Encumbered field range from "Pre Encumbered From" to "Pre Encumbered To"
  When I enter Encumbered field range from "Encumbered From" to "Encumbered To"
  When I enter Accrued Expense field range from "Accrued Expense From" to "Accrued Expense To"
  When I enter Cash Payments field range from "Cash Payments From" to "Cash Payments To"
  When I enter Post Adjustments field range from "Post Adjustments From" to "Post Adjustments To"
  Then  I click on Submit button for budget domain
  Then  the System displays Budget Transactions for "Selected Year" 
   
  @VerifyLabelFields
  
  Scenario: Verify all labels in Budget advanced search
  Then we should see "Labels" in the form
  
@VerifyDefaultValues

Scenario: Verify all Default Values in the form
Then "Budget Year Id" dropdown has "2021" as default value
Then "Agency Id" dropdown has "Citywide(All Agencies)" as default value
Then "Department Id" dropdown has "Select Department" as default value
Then "Expense Category Id" dropdown has "Select Expense Category" as default value
Then "Budget Code Id" dropdown has "Select Budget Code " as default value
Then "Budget Name Id" dropdown has "Select Budget Name" as default value

 
  @PopulateAllFields
  
  Scenario: Populate All fields in advanced search
  When click on Budget Fiscal Year drop down for "Selected Year"   
    When click on Agency dropdown and select "Selected Agency"
    
   When I enter adopted field range from "Adopted From" to "Adopted To" 
  When I enter modified field range from "Modified From" to "Modified To" 
  When I enter Pre Encumbered field range from "Pre Encumbered From" to "Pre Encumbered To"
  When I enter Encumbered field range from "Encumbered From" to "Encumbered To"
  When I enter Accrued Expense field range from "Accrued Expense From" to "Accrued Expense To"
  When I enter Cash Payments field range from "Cash Payments From" to "Cash Payments To"
  When I enter Post Adjustments field range from "Post Adjustments From" to "Post Adjustments To"
  
  When click on Department dropdown and select "Selected Department"
    When click on Expense Category dropdown and select "Selected Expense Category"
    Then I click on Submit button for budget domain
    Then the System displays Budget Transactions for "Selected Year"  

    