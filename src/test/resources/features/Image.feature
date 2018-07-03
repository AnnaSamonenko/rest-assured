Feature: Image

  @See
  Scenario Outline: 1.0 Image verification made by cameras by rover
    When call Mars photos service with <sol> sol for <rover> rover for sol date for <quantity> photos
    And call Mars photos service with <sol> sol for <rover> rover for earth date for <quantity> photos
    Then metadata is the same
    And images are the same

    Examples:
      | sol  | rover     | quantity |
      | 1000 | curiosity | 5        |