@allure.label.epic:Web
@allure.label.parentSuite:ServiceNow
Feature: General Cloud Services Request

@login
@generalCloudServicesRequest
Scenario: ServiceNow General Cloud Services Request

When I navigate to service portal
And I open a general cloud services request
And I fill and submit the general cloud services request
And I navigate to the RITM
And I close the catalog task
Then I should see the closed RITM