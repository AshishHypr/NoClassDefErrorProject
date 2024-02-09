Feature: Home
  This is Web Home Feature file

  @web @home
  Scenario: [UI] Validate Home Elements
    Given I am on "HomePage" web page
    Then I verify that "Mission-driven. People-powered." text is displayed in "mozillaHomeHeader" web element
