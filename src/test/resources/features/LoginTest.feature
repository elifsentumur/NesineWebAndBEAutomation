Feature: Nesine.com Login

  Scenario: Login to Nesine.com with credentials from /getCredentials API
    Given the user is on the Nesine.com login page
    When the user is click accept button
    When the users logs in with valid credentials from /getCredentials API
    And  the user click on the login button
    Then the user should be logged in successfully


  Scenario: Check "Popüler Bahisler" button and compare played counts
    Given the user is on the Nesine.com login page
    When the user is click accept button
    When I click the Popüler Bahisler button
    And I check the page link
    And I select the "Futbol" tab
    And I send a GET request to "https://pb.nesine.com/v1/Bet?eventType=1"
    Then I compare played counts between the response and the webpage

    Scenario: Nesine.com & Node Otomasyon Case study
      Given the user is on the Nesine.com login page
      When the user is click accept button
      When the users logs in with valid credentials from /getCredentials API
      And  the user click on the login button
      When I click the Popüler Bahisler button
      And I check the page link
      And I select the "Futbol" tab
      And I send a GET request to "https://pb.nesine.com/v1/Bet?eventType=1"
      Then I compare played counts between the response and the webpage
      And Go to Logout
