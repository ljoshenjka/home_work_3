@testSuite
@ios
@android
Feature: Application home screen

#  Charter 003
  Scenario: Check main screen base elements
    Then checks that spending categories are present on Main screen
      | Food           |
      | Car            |
      | Transport      |
      | Entertainment  |
      | House          |
      | Taxi           |
      | EatingOut      |
      | Clothes        |
      | Toiletry       |
      | Sports         |
      | Health         |
      | Communications |
    And expense button is present on Main screen
    And income button is present on Main screen
    And user balance is '$0.00'

#  Charter 004
  Scenario: Open category from app main screen
    When user opens 'Car' category from Main screen
    Then checks that New Expense screen shown for category 'Car'

#  Charter 006
  Scenario: Check navigation bar for mandatory elements
    Then checks that navigation bar title is 'Monefy'
    And checks that strawberry menu is present in navigation bar
    And checks that kebab menu is present in navigation bar
    And checks that search button is present in navigation bar
    And checks that transfer button is present in navigation bar
    And checks that 'All accounts' is selected as account