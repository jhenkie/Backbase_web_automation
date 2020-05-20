@regression
Feature: Login feature

    Background: Open browser and navigate to login URL
      Given open browser and navigate to backbase login URL

    Scenario Outline: User fills in invalid credential
      When User goes to sign in page
      And User login with invalid credential "<email>" and "<password>"
      Then User verifies failed "<wrongCredential>"

      Examples:
      |email            |password |wrongCredential              |
      |random@gmail.com |random   |email or password is invalid |

    Scenario Outline: User doesn't fill in username
      When User goes to sign in page
      And User only fills in password "<password>"
      Then User verifies failed "<emptyEmail>"

      Examples:
        |password |emptyEmail           |
        |random   |email can't be blank |

    Scenario Outline: User doesn't fill in password
      When User goes to sign in page
      And User only fills in email "<email>"
      Then User verifies failed "<emptyPassword>"

      Examples:
        |email            |emptyPassword          |
        |random@gmail.com |password can't be blank|

    Scenario: User fills in credentials and login
      When User goes to sign in page
      And User logins with credential
      Then User verifies successful login