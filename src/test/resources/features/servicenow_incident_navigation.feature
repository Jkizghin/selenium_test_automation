Feature: ServiceNow Incident Navigation

@servicenow_auth
@incident
Scenario: Login to ServiceNow

Given I open the browser for authentication
When I navigate to authentication url "https://gditsharedtest.servicenowservices.com"
And I enter authentication username "gdit.selenium.user" and password "gdit.selenium.user"
Then I should be authenticated
When I navigate to incident page
Then I should see incident page loaded