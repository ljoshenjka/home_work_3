@testSuite
@ios
@android
Feature: Application new income screen

#  Charter 007
  Scenario: New income screen is shown
    When user clicks income on Main screen
    Then checks that New Income screen shown

#  Charter 008
  Scenario: Enter new income
    When user clicks income on Main screen
    And user enters new balance '100'
    Then checks that balance is '100'

#  Charter 009
  Scenario: Calculator functionality
    When user clicks income on Main screen
    And user enters new balance '5+5='
    Then checks that balance is '10'
    When user enters new balance '*3='
    Then checks that balance is '30'
    When user enters new balance '/2='
    Then checks that balance is '15'
    When user enters new balance '-0.2='
    Then checks that balance is '14.8'

#  Charter 010
#  Charter 011
  Scenario: Submit new salary income
    When user clicks income on Main screen
    And user enters new balance '100'
    And user clicks Choose Category
    Then user selects 'Salary' category from balance screen
    And user balance is '$100.00'
    And user income is '$100.00'