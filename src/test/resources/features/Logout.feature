@regression
Feature: Logout feature

  Background: Open browser and navigate to login URL
    Given open browser and navigate to backbase login URL
    When User goes to sign in page
    And User logins with credential

  Scenario: User fills in credentials and login
    And User goes to settings page
    And User clicks logout button
    Then User verifies successful logout


