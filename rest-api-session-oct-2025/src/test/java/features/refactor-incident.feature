Feature: Validating CRUD opeations of the incident table

Background:
Given user should set the base uri "https://dev324941.service-now.com" of the incident table service
And user should set the base path "/api/now/table" of the incident table service
And user should set basic auth authencation username "admin" and password "e5!pRsPN%lH5" for the incident table service

@smoke @regression
Scenario: Validate user should able to fecth all the records from the incident table
When user hit get method of the "/incident" table service to get all records
Then user should see the status code as "200" in the repose
And user should see the status line as "OK" in the repose
And user should get the response in the JSON format

@regression
Scenario: Validate user should able to fecth all the records from the incident table in the xml format
Given user should add header with key as "Accept" and value as "application/xml"
When user hit get method of the "/incident" table service to get all records
Then user should see the status code as "200" in the repose
And user should see the status line as "OK" in the repose
And user should get the response in the XML format

@smoke @regression
Scenario: Validate user should able to create new incident record with out request body
Given user should add header with key as "Content-Type" and value as "application/json"
When user hit post method of the "/incident" table service to create new record
Then user should see the status code as "201" in the repose
And user should see the status line as "Created" in the repose
And user should get the response in the JSON format

@regression
Scenario: Validate user should able to create new incident record with JSON request body
Given user should add header with key as "Content-Type" and value as "application/json"
When user should add shortdescription as "RESTAPISESSIONOCT2025" in the request body
And user should add category as "software" in the request body
When user hit post method of the "/incident" table service with JSON payload to create new record
Then user should see the status code as "201" in the repose
And user should see the status line as "Created" in the repose
And user should get the response in the JSON format