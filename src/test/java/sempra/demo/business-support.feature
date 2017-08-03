Feature: Spectrum business online support
  Spectrum Business customers should be able find online help using the support portal

  Scenario: Frequently asked support queries regarding internet 
    Given I am on www.spectrumbusiness.net portal
    When I click on the Support link
    When I click on the Internet link
    Then I should be able to see the list of internet related troubleshooting tips
