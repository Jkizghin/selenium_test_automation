@allure.label.epic:Web
@allure.label.parentSuite:ServiceNow
Feature: Incident Navigation

@login

@incident
Scenario: ServiceNow Incident Navigation

When I navigate to incident page
And I enter incident values
And I close the incident
Then I should see the closed incident