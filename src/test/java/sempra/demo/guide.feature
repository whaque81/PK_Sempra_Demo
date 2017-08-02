Feature: TV Guide
  As a user of Spectrum cable I should be able to view its TV guide on www.spectrum.net portal

  Scenario: View latest TV Guide
    Given I am on www.spectrum.net website
    When I click on the Guide link
    Then I should be redirected to the TV Guide page
