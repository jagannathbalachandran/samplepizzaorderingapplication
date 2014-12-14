@order
Feature: Order pizza

Background:
Given I am on login page
When I sign in

Scenario Outline: User can order a pizza
  When I select a <size> size <pizza> pizza with toppings:
  |name|isDouble|
  |Onion|true   |
  |Tomato|false |
  When I order
  Then the order should be placed for the pizza
Examples:
| pizza | size |
|Veggie Panneer | medium |
|Mexican Veg | Large |


  Scenario Outline: User can order a pizza from the listing page
    When I select a <size> size <pizza> pizza with toppings:
      |name|isDouble|
      |Onion|true   |
      |Tomato|false |
    Then the order should not be placed for the pizza
    When I order from the order listing page
    Then the order should be placed for the pizza

  Examples:
    | pizza | size |
    |Veggie Panneer | medium |

