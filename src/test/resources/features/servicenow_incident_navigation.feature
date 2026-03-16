@allure.label.epic:Web
@allure.label.parentSuite:ServiceNow
Feature: Incident Navigation

@servicenow_auth
@incident
Scenario: ServiceNow Incident Navigation

Given I open the browser for authentication
When I navigate to authentication url "https://gditsharedtest.servicenowservices.com"
And I enter authentication username "username" and password "password"
Then I should be authenticated
When I navigate to incident page
And I enter incident values
And I close the incident
Then I should see the closed incident