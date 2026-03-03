Feature: ServiceNow Incident Navigation

@servicenow_auth
@incident
Scenario: Login to ServiceNow

Given I open the browser for authentication
When I navigate to authentication url "https://gditsharedtest.servicenowservices.com"
And I enter authentication username "username" and password "password"
Then I should be authenticated
When I navigate to incident page
And I enter incident values
Then I should see incident page loaded