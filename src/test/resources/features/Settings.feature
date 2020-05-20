@regression
Feature: Setting feature

  Background: Open browser and navigate to login URL
    Given open browser and navigate to backbase login URL
    When User goes to sign in page
    And User logins with credential

  Scenario Outline: User updates detail settings
    When User goes to settings page
    And User updates settings detail"<newName>" "<newBio>"
    Then User verifies updated settings "<newName>" "<newBio>"
    And User reverts data back oldName "<oldBio>"
    Then User verifies updated settings oldName "<oldBio>"

    Examples:
    |newName     |newBio                                     |oldBio                                 |
    |jhenkieiscool |ordinary man with extra ordinary dream new |ordinary man with extra ordinary dream |

  Scenario Outline: User updates main settings
    When User goes to settings page
    And User updates settings main"<newEmail>"
    And User goes to settings page
    And User clicks logout button
    And User logins with new credential "<newEmail>"
    And User goes to settings page
    And User reverts data back
    And User goes to settings page
    And User clicks logout button
    Then User logins with credential

    Examples:
    |newEmail           |
    |johannesiscool@gmail.com |
