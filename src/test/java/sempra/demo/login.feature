Feature: Login
  As a Spectrum customer I should be able to login into the Spectrum portal

  Scenario Outline: Login using valid credentials
    Given I am on www.spectrum.net portal and I click on Sign In button
    When I enter valid credentials and click on the Sign In button
    Then I should be able to login into Spectrum portal

    Examples: 
      | user                      | password  |
      | autoqa003_hoh@charter.net | Testing01 |
