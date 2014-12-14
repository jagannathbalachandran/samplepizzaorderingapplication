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
  Then the pizza should be shown in the list of orders
Examples:
 | pizza | size |
 |Veggie Panneer | medium |
# |Mexican Veg | Large |




