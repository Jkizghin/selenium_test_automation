Feature: ServiceNow Authentication

@servicenow_auth
Scenario: Login to ServiceNow

Given I open the browser for authentication
When I navigate to authentication url "https://gditsharedtest.servicenowservices.com/"
And I enter authentication username "YOUR_USERNAME" and password "YOUR_PASSWORD"
Then I should be authenticated