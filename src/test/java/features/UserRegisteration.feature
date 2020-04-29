Feature: User Registration
  I want to check that the user can register to the website
  Scenario: User Registration
    Given  the user is in the home page
    When I click on register button
    And I entered the user data
    Then The registration page displayed successfully
