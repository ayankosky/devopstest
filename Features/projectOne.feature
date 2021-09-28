#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions

Feature: Navigating project one
Background: 
Given Already at localhost:3000/login.html

Scenario: Logging in as a manager with correct credentials
When a user inputs correct username
And a user inputs correct password
Then a user is sent to the manager page

Scenario: Logging in as a employee with correct credentials
When a user inputs correct username
And a user inputs correct password
Then a user is sent to the employee page

Scenario: Logging in with incorrect credentials
When a user inputs incorrect username
And a user inputs incorrect password
Then a user remains on the login page

Background:
Given user is logged in as a manager

Scenario: user wants to filter requests by Employee
When user selects name from dropdown
Then user is shown only requests by that employee

Scenario: user wants to approve/deny a request
When user selects option from drop down list
Then user shown a reflected page with change made

Background:
Given user is logged in as a employee

Scenario: user wants to submit a new request
When user clicks create request button
Then user directed to a form submission page

Scenario: user fills out form for new request
When user inputs type of request
And user inputs correct amount
Then user is redirected to employee page with new request pending

Scenario: user logouts 
When user clicks logout
Then user is redirected to the login page

