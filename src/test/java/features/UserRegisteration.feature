Feature: User Registration
  I want to check that the user can register to the website
 Scenario Outline: User Registration
    Given  the user is in the home page
    When I click on register button
    And I entered the "<username>" , "<phoneNumber>" ,"<email>" ,"<password>"
    Then The registration page displayed successfully
   Examples:
   | username | phoneNumber | email | password |
   |mandoooh  | 0125547788  | tetooprimaryx@test.com | 12345678 |
