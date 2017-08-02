Feature: Spectrum store location search
  Potential customers should be able to search nearest stores based on zip code

  Scenario: Search Spectrum stores within 100 miles of zipcode 80237
    Given I am on www.spectrum.net portal
    When I click on the Locations link
    Then I should be redirected to Spectrum Service page
    When I enter zip code and distance within and click on the submit button
    Then I should be able to see the list of service centers near me
