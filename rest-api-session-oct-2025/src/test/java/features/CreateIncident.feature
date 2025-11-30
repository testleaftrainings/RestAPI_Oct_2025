Feature: Create the new record in the incident table

Scenario: User should able create new record using file as request body
Given user should set the base uri "https://dev324941.service-now.com" of the incident table service
And user should set the base path "/api/now/table" of the incident table service
And user should set basic auth authencation username "admin" and password "e5!pRsPN%lH5" for the incident table service
And user should add header with key as "Content-Type" and value as "application/json"
When user should use "src/main/resources/servicenow/create_incident.json" file path to send request body
And user hit post method of the "/incident" table service to create new via File
Then user should validate the response component with the expected value
| statusCode | statusLine | contentType      |
| 201        | Created    | JSON             |
And user extract the sysid from the response body