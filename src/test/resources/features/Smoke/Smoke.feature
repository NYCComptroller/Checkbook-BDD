@Smoke
Feature: Checkbook Smoke test
  As a user I want to go to Checkbook home page so that I can view the basic features 
  Background: 
    Given I navigate to Checkbook home Page
            
    @HomeCitywide    
   Scenario: Navigate to Checkbook home page and verify all menu items on the page    
   Then the System displays top menu items
    
       Then the System displays New Features menu title  
     
     @HomeTools
       Scenario: Navigate to Checkbook home page and verify Trends menu items on the page    
    Then the System displays Trends sub menu titles
    
      Then the System displays Tools sub menu titles
     Then the System displays Data Feeds menu title
       Then the System displays Help sub menu titles
     Then the System displays New Features menu title
     Then the System displays Resources sub menu titles
       Then the System displays New Features menu title
        Then the System displays Resources sub menu titles