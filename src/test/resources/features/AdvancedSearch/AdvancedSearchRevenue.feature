@AdvancedSearchRevenue
Feature: Checkbook Advanced Search Nycha Revenue Tab
  As a user I want to go to Nycha Revenue Tab so that I can view the Nycha Revenue Details

  Background: 
    Given I navigate to revenue Advanced search form Page
   
   @PopulateRevenueAdvancedSearch
   Scenario: Populate Revenue data and click on dropdowns
    Then click on revenue fy drop down for "Selected Year"
    And click on funding class dropdown for value "Funding Class"
    And click on agency dropdown and select agency "Agency"
    And I click on Submit button for revenue domain
    Then the System displays Revenue Transactions for "Selected Year"  
    
  @PopulateTextFields
  Scenario: Populate text fields in advanced search
  Then enter "Revenue Source" as value to field revenue source 
  And enter "Revenue Class" as value to field revenue class
  And I click on Submit button for revenue domain
  Then the System displays Revenue Transactions for "Selected Year"  
  
  @PopulateRangeFields
  Scenario: Populate range fields in advanced search
  When I enter adopted field range from "Adopted From" to "Adopted To" 
  When I enter modified field range from "Modified From" to "Modified To" 
  When I enter recognized field range from "Recognized From" to "Recognized To"
  And  I click on Submit button for revenue domain
  Then  the System displays Revenue Transactions for "Selected Year" 
   
  @VerifyLabelFields
  
  Scenario: Verify all labels in Revenue advanced search
  Then we should see "Labels" in the form
  
@VerifyDefaultValues

Scenario: Verify all Default Values in the form
Then "Budget FY Id" dropdown has "2021" as default value
And  "Fiscal Year Id" dropdown has "All Fiscal Years" as default value
And "Agency Id" dropdown has "Citywide(All Agencies)" as default value
And "Funding Class Id" dropdown has "All Funding Classes" as default value
And "Revenue Category Id" dropdown has "All Revenue Categories" as default value
And "Fund Class Id" dropdown has "General Fund" as default value
 
  @PopulateAllFields
  
  Scenario: Populate All fields in advanced search
  When I enter adopted field range from "Adopted From" to "Adopted To" 
  When I enter modified field range from "Modified From" to "Modified To" 
  When I enter recognized field range from "Recognized From" to "Recognized To"
   Then click on revenue fy drop down for "Selected Year"
    And click on funding class dropdown for value "Funding Class"
    And click on agency dropdown and select agency "Agency"
    Then enter "Revenue Source" as value to field revenue source 
  And enter "Revenue Class" as value to field revenue class
  And I click on Submit button for revenue domain
  Then the System displays Revenue Transactions for "Selected Year"  