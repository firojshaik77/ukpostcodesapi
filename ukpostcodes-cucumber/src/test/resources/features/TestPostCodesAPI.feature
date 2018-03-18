Feature: To Test UK Post Codes API elements
  Scenario: User calls api with a PostCode
    Given a user provides postcode OX49 5NU
    When user calls the postcode API
    Then the status code is 200
    And response includes the following
      | result.eastings | 464440 |
      | result.country | England |
      | result.nuts | Oxfordshire |
      | result.codes.nuts | UKJ14 |

  Scenario Outline: User calls api with multiple PostCodes
    Given a user provides postcode <postcode>
    When user calls the postcode API
    Then the status code is 200

    Examples:
      | postcode |
      | M32 0JG   |
      | NE30 1DP  |

  Scenario: User calls api with invalid PostCode
    Given a user wants to verify the response status
    When user calls the API with xyz
    Then the status code is 404
    And the error is "HTTP/1.1 404 Not Found"


  Scenario Outline: User calls api with multiple Longitude & Latitude values
    Given a user provides "<longitude>" in param "lon" and "<latitude>" in param "lat"
    When user calls the postcode API
    Then the status code is 200

    Examples:

      | longitude         | latitude |
      | -1.06986930435083   | 51.656143706615  |
      | -2.30283674284007   | 53.4556572899372 |


  Scenario: User calls api's POST request with geolocation JSON data
    Given a set of GeoLocation data
    """
    {
    "geolocations" : [{"longitude": -2.49690382054704,"latitude": 53.5351312861402,"radius": 1000,"limit": 5}]
    }
    """
    When user makes a POST request
    Then the status code is 200