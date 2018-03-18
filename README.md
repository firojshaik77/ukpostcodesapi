# ukpostcodesapi
Test Assignment for UK Post codes API using cucumber

This Project is to test the Postcode ans Geolocation API for the UK using Cucumber,Gherkin,Java,Maven and RestAssured.
API Endpoint used in this project: http://api.postcodes.io/postcodes

This covers the following scenarios
1.User calls api with a PostCode . This test is executed using "Query for postcode" GET method with 
api request as api.postcodes.io/postcodes?q=<postcode>

2. User calls api with multiple PostCodes .These tests are executed using "Query for postcode" GET method 
with api request as api.postcodes.io/postcodes?q=<postcode>

3.User calls api with invalid PostCode (Negative Scenario). This test is executed using "Lookup a postcode" GET
method with api request as api.postcodes.io/postcodes/xyz  

4.User calls api with multiple Longitude & Latitude values. This test is executed using "Get nearest postcodes for a given 
longitude & latitude" GET method with api request as api.postcodes.io/postcodes?lon=<longitude>&lat=<latitude>

5.User calls api's POST request with geolocation JSON data. This test is executed using "Bulk Reverse Geocoding" POST method 
with api request as api.postcodes.io/postcodes <Geo location data>


This Project can be executed in 3 different ways from Eclipse.
1. Select "Pom.xml" file. Right click and choose Run As -> Maven test.
2. Select "TestPostCodesAPI.feature" file. Right click and choose Run As ->Cucumber Feature.
3. Select "PostCodesCucumberTest.java" file. Right click and choose Run As ->JUnit Test.
 
