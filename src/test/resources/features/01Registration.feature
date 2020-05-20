@regression
Feature: Registration feature

  Background: Open browser and navigate to login URL
    Given open browser and navigate to backbase login URL

  Scenario: User fills in empty data
    When User goes to sign up page
    And User register with empty details
    Then User verifies error register

  Scenario: User fills in credentials and login
    When User goes to sign up page
    And User register with details
    Then User verifies successful login
