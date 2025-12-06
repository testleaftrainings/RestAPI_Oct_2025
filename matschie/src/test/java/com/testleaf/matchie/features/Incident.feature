Feature: Validating CRUD opeations of the incident table

@smoke @regression
Scenario: Validate user should able to fecth all the records from the incident table
Given user should set the base uri "https://dev324941.service-now.com" of the incident table service
And user should set the base path "/api/now/table" of the incident table service
And user should set basic auth authencation username "admin" and password "e5!pRsPN%lH5" for the incident table service
When user hit get method of the "/incident" table service to get all records
Then user should see the status code as "200" in the repose
And user should see the status line as "OK" in the repose
And user should get the response in the "JSON" format