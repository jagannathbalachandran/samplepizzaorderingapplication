package pages;

import entity.Pizza;
import entity.Topping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.PropertyReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertTrue;


public class OrderListingPage {
    public static final int PIZZA_NAME_COLUMN_INDEX = 0;
    public static final int PIZZA_SIZE_COLUMN_INDEX = 1;
    public static final int PIZZA_NO_OF_TOPPINGS_COLUMN_INDEX = 2;
    public static final int PIZZA_ORDER_PLACED_COLUMN_INDEX = 3;
    public static final int PIZZA_SHOW_DETAILS_COLUMN_INDEX = 4;

    private WebDriver webDriver;

    private PropertyReader reader;

    public OrderListingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        reader = new PropertyReader();
        assertTrue(webDriver.getCurrentUrl().equals(reader.readProperty("url") + "/pizzas"));
        PageFactory.initElements(webDriver, this);
    }

    public boolean isOrderPlacedFor(Pizza pizza) {

        List<WebElement> rows = webDriver.findElement(By.cssSelector("body table")).findElements(By.tagName("tr"));
        for (Iterator<WebElement> iterator = rows.iterator(); iterator.hasNext(); ) {
            WebElement row = iterator.next();
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if(columns.isEmpty()) continue;  //header
            Pizza currPizza = getPizzaFromCurrentRow(columns.get(PIZZA_NAME_COLUMN_INDEX).getText() ,columns.get(PIZZA_SIZE_COLUMN_INDEX).getText() );
            String noOfToppings = columns.get(PIZZA_NO_OF_TOPPINGS_COLUMN_INDEX).getText();
            if(pizza.equals(currPizza) && Integer.valueOf(pizza.getNoOfToppings()).equals(Integer.valueOf(noOfToppings))) {
                return isOrderPlaced(columns.get(PIZZA_ORDER_PLACED_COLUMN_INDEX).getText());
            }
        }
        return false;
    }

    private boolean isOrderPlaced(String text) {
        return Boolean.valueOf(text.trim());
    }

    private Pizza getPizzaFromCurrentRow(String pizzaName , String size) {
        return new Pizza(pizzaName , size , new ArrayList<Topping>());
    }

    public EditPizzaPage viewDetails(Pizza pizza) throws Exception {
        List<WebElement> rows = webDriver.findElement(By.cssSelector("body table")).findElements(By.tagName("tr"));
        for (Iterator<WebElement> iterator = rows.iterator(); iterator.hasNext(); ) {
            WebElement row = iterator.next();
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if(columns.isEmpty()) continue;  //header
            Pizza currPizza = getPizzaFromCurrentRow(columns.get(PIZZA_NAME_COLUMN_INDEX).getText() ,columns.get(PIZZA_SIZE_COLUMN_INDEX).getText() );
            String noOfToppings = columns.get(PIZZA_NO_OF_TOPPINGS_COLUMN_INDEX).getText();
            if(pizza.equals(currPizza) && Integer.valueOf(pizza.getNoOfToppings()).equals(Integer.valueOf(noOfToppings))) {
                columns.get(PIZZA_SHOW_DETAILS_COLUMN_INDEX).click();
                return new EditPizzaPage(webDriver);
            }
        }

        throw new Exception("Not able to find the pizza " + pizza);

    }
}
