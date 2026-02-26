Feature: Open Google

Scenario: Verify Google page opens
Given I open the browser
When I navigate to  "https://google.com"
Then the page title should contain "Google"