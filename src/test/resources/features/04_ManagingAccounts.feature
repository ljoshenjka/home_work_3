@testSuite
@ios
@android
Feature: Application account management

#  Charter 018
  Scenario: Check available accounts in left menu
    When user opens left navigation menu
    And user clicks account spinner in left navigation menu
    Then account types shown in spinner
      | All accounts |
      | Cash         |
      | Payment card |

#  Charter 019
  Scenario: Check available accounts in right menu
    When user opens right navigation menu
    And user select Accounts from right navigation menu
    Then account types shown in accounts menu
      | Cash         |
      | Payment card |