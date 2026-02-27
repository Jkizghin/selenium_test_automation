Feature: ServiceNow Navigation
@servicenow
Scenario: Verify ServiceNow page opens
Given I open the browser
When I navigate to "https://gditsharedtest.servicenowservices.com/"
Then the page title should contain "GDIT" 
Then I wait 5 seconds