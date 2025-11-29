Feature: Validating CRUD opeations of the incident table

Background:
Given user should set the base uri "https://dev324941.service-now.com" of the incident table service
And user should set the base path "/api/now/table" of the incident table service
And user should set basic auth authencation username "admin" and password "e5!pRsPN%lH5" for the incident table service

@smoke @regression
Scenario: Validate user should able to fecth all the records from the incident table
When user hit get method of the "/incident" table service to get all records
Then user should validate the response component with the expected value
| statusCode | statusLine | contentType      |
| 200        | OK         | JSON             |

@regression
Scenario: Validate user should able to fecth all the records from the incident table in the xml format
Given user should add header with key as "Accept" and value as "application/xml"
When user hit get method of the "/incident" table service to get all records
Then user should validate the response component with the expected value
| statusCode | statusLine | contentType      |
| 200        | OK         | XML              |

@smoke @regression
Scenario: Validate user should able to create new incident record with out request body
Given user should add header with key as "Content-Type" and value as "application/json"
When user hit post method of the "/incident" table service to create new record
Then user should validate the response component with the expected value
| statusCode | statusLine | contentType      |
| 201        | Created    | JSON             |

@regression
Scenario Outline: Validate user should able to create new incident record with JSON request body
Given user should add header with key as "Content-Type" and value as "application/json"
When user should add shortdescription as "<short_description>" in the request body
And user should add category as "<category>" in the request body
And user hit post method of the "/incident" table service with JSON payload to create new record
Then user should validate the response component with the expected value
| statusCode | statusLine | contentType      |
| 201        | Created    | JSON             |

Examples:
| short_description       | category |
| RESTAPISESSIONOCT2025-1 | software |
| RESTAPISESSIONOCT2025-2 | hardware |
| RESTAPISESSIONOCT2025-3 | inquiry  |

@regression
Scenario Outline: Validate user should able to create new incident record with JSON request body using data table
Given user should add header with key as "Content-Type" and value as "application/json"
When user should add the relevant value to create record as request paylod
| "<short_description>" | "<category>" |
And user hit post method of the "/incident" table service with JSON payload to create new record
Then user should validate the response component with the expected value
| statusCode | statusLine | contentType      |
| 201        | Created    | JSON             |

Examples:
| short_description       | category |
| RESTAPISESSIONOCT2025-1 | software |
| RESTAPISESSIONOCT2025-2 | hardware |
| RESTAPISESSIONOCT2025-3 | inquiry  |
