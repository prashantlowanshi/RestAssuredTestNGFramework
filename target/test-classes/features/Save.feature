Feature: Save

#  Scenario: Create playlist with valid details
#    Given We have created the post body
#    When We get the response
#    Then the values should match

  Scenario: Create save settings for Shift4Shop
    Given We have added the json body for post request
    When Request to save the setting is done
    Then Correct status code should be displayed

