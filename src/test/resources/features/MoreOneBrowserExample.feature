Feature: Login to Nesine.com with Different Browsers

  Scenario: Login with Chrome,Firefox,Edge browser
    Given I am on the Nesine.com login page using
    When the user is click accept button
    When the users logs in with valid credentials from /getCredentials API
    And  the user click on the login button

#  Scenario: Check "Popüler Bahisler" button and compare played counts
#    Given I am on the Nesine.com login page using
#    When the user is click accept button
#    When I click the Popüler Bahisler button
#    And I check the page link
#    And I select the "Futbol" tab
#    And I send a GET request to "https://pb.nesine.com/v1/Bet?eventType=1"
#    Then I compare played counts between the response and the webpage