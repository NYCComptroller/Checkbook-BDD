@AdvancedSearchPayroll
Feature: Checkbook Advanced search Citywide Payroll

  As a user I want to go to Advanced Search Citywide Payroll form so that I can view the Citywide Payroll Details

  Background: 
    Given I navigate to payroll Advanced search form Page
   
   @PopulatePayrollAdvancedSearch
   Scenario: Populate data and click on drop downs
    Then click on Payroll year drop down and select "Year"
    Then click on Pay Frequency drop down and select "Pay Frequency"
    Then click on agency drop down and select "Agency"
    Then click on salaried type radio button and select "Salaried Type"
    Then I click on Submit button for payroll domain
    Then the System displays Payroll Transactions for "Year"  
    
  @PopulateTextFields
  Scenario: Populate text fields in advanced search
  Then enter "Title" as value to field Title
  Then I click on Submit button for payroll domain
  Then the System displays Payroll Transactions for "Year"  
  
  @PopulateRangeFields
  Scenario: Populate range fields in advanced search
  When I enter Base Pay field range from "Base Pay From" to "Base Pay To" 
  When I enter Overtime Payments field range from "Overtime Payments From" to "Overtime Payments To" 
  When I enter Other Payments field range from "Other Payments From" to "Other Payments To"
  When I enter Gross Pay YTD field range from "Gross Pay YTD From" to "Gross Pay YTD To"
  When I enter Gross Pay field range from "Gross Pay From" to "Gross Pay To"
  When I enter Amount field range from "Amount From" to "Amount To"
  When I enter Pay Date field range from "Pay Date From" to "Pay Date To"
  Then I click on Submit button for payroll domain
  Then the System displays Payroll Transactions for "Year"
   
  @VerifyLabelFields
  
  Scenario: Verify all labels in Payroll advanced search
  Then we should see "Labels" in the form
  
@VerifyDefaultValues

Scenario: Verify all Default Values in the form
Then "Payroll Year Id" drop down has "CY 2020" as default value
Then "Agency Id" drop down has "Citywide(All Agencies)" as default value
Then "Pay Frequency Id" drop down has "Select Pay Frequency" as default value
Then "Salaried Type Id" radio button has "All" as default value

 
  @PopulateAllFields
  
  Scenario: Populate All fields in advanced search
 
 When I enter Base Pay field range from "Base Pay From" to "Base Pay To" 
  When I enter Overtime Payments field range from "Overtime Payments From" to "Overtime Payments To" 
  When I enter Other Payments field range from "Other Payments From" to "Other Payments To"
  When I enter Gross Pay YTD field range from "Gross Pay YTD From" to "Gross Pay YTD To"
  When I enter Gross Pay field range from "Gross Pay From" to "Gross Pay To"
  When I enter Amount field range from "Amount From" to "Amount To"
  When I enter Pay Date field range from "Pay Date From" to "Pay Date To"
  Then enter "Title" as value to field Title
  Then click on Payroll year drop down and select "Year"
    Then click on Pay Frequency drop down and select "Pay Frequency"
    Then click on agency drop down and select "Agency"
    Then click on salaried type radio button and select "Salaried Type"
    Then I click on Submit button for payroll domain
    Then the System displays Payroll Transactions for "Year"  
  
     