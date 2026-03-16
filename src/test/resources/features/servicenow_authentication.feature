@allure.label.epic:Web
@allure.label.parentSuite:ServiceNow
Feature: Authentication

@servicenow_auth
Scenario: ServiceNow Authentication

Given I open the browser for authentication
When I navigate to authentication url "https://gditsharedtest.servicenowservices.com"
And I enter authentication username "username" and password "password"
Then I should be authenticated