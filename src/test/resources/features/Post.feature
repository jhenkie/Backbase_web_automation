@regression
Feature: Post feature

  Background: Open browser and navigate to login URL
    Given open browser and navigate to backbase login URL
    When User goes to sign in page
    And User logins with credential

  Scenario: User creates new post
    When User goes to new post page
    And User creates post with details
    Then User verifies successful created post

  Scenario: User deletes created post
    When User goes to profile page
    And User goes to article
    And User click delete article button
    Then User verifies successful deleted article