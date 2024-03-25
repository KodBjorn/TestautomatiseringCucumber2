Feature: Testing the Supporter Account signup

  Scenario Outline: Basket case
    Given Navigate to basket login page using "<driver>"
    Given I have entered date of birth "7/7/1995"
    Given I have entered first name "John" and last name "<last name>"
    Given I have entered a version of email address Johndoe@email.com and confirmed it
    Given I have entered password "MegaStrong" and "<confirmed>" it
    Given I have checked "<tac>", age and code of ethics and conduct
    When  I click the confirm and join button
    Then  I verify "<text>" on the site
    
    Examples:
      | driver | last name | confirmed | tac | text |
      | chrome |    doe    |   true    |true | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND|
      | chrome |           |   true    |true | Last Name is required|
      | chrome |    doe    |   false   |true | Confirm Password is required|
      | chrome |    doe    |   true    |false| You must confirm that you have read and accepted our Terms and Conditions|
      | edge   |    doe    |   true    |true | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND|
      | edge   |           |   true    |true | Last Name is required|
      | edge   |    doe    |   false   |true | Confirm Password is required|
      | edge   |    doe    |   true    |false| You must confirm that you have read and accepted our Terms and Conditions|