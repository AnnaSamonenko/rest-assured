Feature: Image

  Scenario: 1 Image verification made by cameras by rover
    When call Mars photos service with 1000 sol for curiosity rover for sol date for 5 photos
    And call Mars photos service with 1000 sol for curiosity rover for earth date for 5 photos
    Then metadata is the same
    And images are the same
