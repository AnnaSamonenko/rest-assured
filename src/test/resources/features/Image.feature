Feature: Image

  Scenario: 1 Image verification made by cameras of Curiosity rover
    When call Mars photos service with 1000 sol for sol date for 5 photos
    And call Mars photos service with 1000 sol of earth date for 5 photos
    Then metadata is the same
    And images are the same
