@testSuite
@ios
@android
Feature: Application new expense screen

#  Charter 012
  Scenario: New expense screen is shown
    When user clicks expense on Main screen
    Then checks that New Expense screen shown

#  Charter 013
  Scenario: Enter new income
    When user clicks expense on Main screen
    And user enters new balance '100'
    Then checks that balance is '100'

#  Charter 014
  Scenario: Calculator functionality
    When user clicks expense on Main screen
    And user enters new balance '5+5='
    Then checks that balance is '10'
    When user enters new balance '*3='
    Then checks that balance is '30'
    When user enters new balance '/2='
    Then checks that balance is '15'
    When user enters new balance '-0.2='
    Then checks that balance is '14.8'

#  Charter 015
#  Charter 016
  Scenario: Submit new car expense
    When user clicks expense on Main screen
    And user enters new balance '20'
    And user clicks Choose Category
    Then user selects 'Car' category from balance screen
    And user balance is '-$20.00'
    And user expense is '$20.00'

#  Charter 017
  Scenario: Submit new car expense from Main screen
    When user opens 'Car' category from Main screen
    And user enters new balance '20'
    And user clicks Choose Category
    Then user balance is '-$20.00'
    And user expense is '$20.00'