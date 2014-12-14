package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entity.Pizza;
import entity.Topping;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.OrderListingPage;
import pages.OrderPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertTrue;

public class OrderSteps {

    private WebDriver webDriver;
    private HomePage homePage;
    private OrderPage orderPage;
    private OrderListingPage orderListingPage;
    private SharedContext sharedContext;

    public OrderSteps(SharedContext sharedContext){
        this.sharedContext = sharedContext;
        webDriver = sharedContext.getDriver();
        orderPage = new OrderPage(webDriver);
        orderListingPage = new OrderListingPage(webDriver);
        homePage = new HomePage(webDriver);
    }

    @When("^I select a ([^\"]*) size ([^\"]*) pizza with toppings:$")
    public void i_select_a_size_pizza_with_toppings(String size, String pizza, DataTable dataTable) throws Throwable {
        homePage.navigateToOrderPage();
        Random random = new Random();
        String pizzaName = pizza + random.nextInt();
        List<Topping> toppings = asToppings(dataTable);
        sharedContext.setPizza(new Pizza(pizzaName , size , toppings));
        orderPage.selectPizza(pizzaName , size);
        orderPage.addToppings(toppings);
    }

    @When("^I order$")
    public void I_order() throws Throwable {
        orderPage.order();
    }

    @Then("^the pizza should be shown in the list of orders$")
    public void the_size_pizza_should_be_shown_in_the_list_of_orders() throws Throwable {
        assertTrue("Order for " + sharedContext.getPizza() +" is not listed" , orderListingPage.isOrderPlacedFor(sharedContext.getPizza()));
    }

    private List<Topping> asToppings(DataTable dataTable) {
        List<Topping> toppings =  new ArrayList<Topping>();
        toppings = dataTable.asList(Topping.class);
        return toppings;
    }
}
