package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entity.Pizza;
import entity.Topping;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertTrue;

public class OrderSteps {

    private WebDriver webDriver;
    private HomePage homePage;
    private NewPizzaPage newPizzaPage;
    private EditPizzaPage editPizzaPage;
    private OrderListingPage orderListingPage;
    private SharedContext sharedContext;

    public OrderSteps(SharedContext sharedContext){
        this.sharedContext = sharedContext;
        webDriver = sharedContext.getDriver();
        homePage = new HomePage(webDriver);
    }

    @When("^I select a ([^\"]*) size ([^\"]*) pizza with toppings:$")
    public void i_select_a_size_pizza_with_toppings(String size, String pizza, DataTable dataTable) throws Throwable {
        newPizzaPage = homePage.navigateToOrderPage();
        Random random = new Random();
        String pizzaName = pizza + random.nextInt();
        List<Topping> toppings = asToppings(dataTable);
        sharedContext.setPizza(new Pizza(pizzaName , size , toppings));
        editPizzaPage = newPizzaPage.selectPizza(pizzaName, size);
        editPizzaPage.addToppings(toppings);
    }

    @When("^I order$")
    public void I_order() throws Throwable {
        orderListingPage = editPizzaPage.order();
    }

    @When("^I order from the order listing page$")
    public void I_order_from_the_order_listing_page() throws Throwable {
        orderListingPage.viewDetails(sharedContext.getPizza());
        editPizzaPage.order();
    }

    @Then("^the order should be placed for the pizza$")
    public void the_size_pizza_should_be_shown_in_the_list_of_orders() throws Throwable {
        orderListingPage = homePage.navigateToOrderListingPage();
        assertTrue("Order for " + sharedContext.getPizza() +" should be placed" , orderListingPage.isOrderPlacedFor(sharedContext.getPizza()));
    }

    @Then("^the order should not be placed for the pizza$")
    public void the_size_pizza_should_not_be_shown_in_the_list_of_orders() throws Throwable {
        orderListingPage = homePage.navigateToOrderListingPage();
        assertTrue("Order for " + sharedContext.getPizza() +" should not be placed" , !orderListingPage.isOrderPlacedFor(sharedContext.getPizza()));
    }

    private List<Topping> asToppings(DataTable dataTable) {
        List<Topping> toppings =  new ArrayList<Topping>();
        toppings = dataTable.asList(Topping.class);
        return toppings;
    }


}
