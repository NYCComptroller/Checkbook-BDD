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
  
  @PopulateAllFields
  Scenario: Populate All fields in advanced search
  When I enter adopted field range from "1000000" to "200000000" 
  When I enter modified field range from "1000000" to "200000000"
  When I enter recognized field range from "3000000" to "8000000"
   Then click on revenue fy drop down for "Selected Year"
    And click on funding class dropdown for value "Funding Class"
    And click on agency dropdown and select agency "Agency"
    Then enter "teachers of tomorrow" as value to field revenue source 
  And enter "education" as value to field revenue class
  And I click on Submit button for revenue domain
  Then the System displays Revenue Transactions for "Selected Year"  