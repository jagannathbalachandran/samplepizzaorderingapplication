package pages;

import entity.Topping;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.PropertyReader;

import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertTrue;


public class NewPizzaPage {

    private WebDriver webDriver;

    @FindBy(id = "pizza_name")
    private WebElement pizza_name_field;

    @FindBy(id = "topping_name")
    private WebElement topping_name_field;

    @FindBy(id = "pizza_size")
    private WebElement pizza_size_field;

    @FindBy(id = "topping_double_order")
    private WebElement topping_double_order_field;

    @FindBy(name = "commit")
    private WebElement create_button;

    PropertyReader reader;

    public NewPizzaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        reader = new PropertyReader();
        assertTrue(webDriver.getCurrentUrl().equals(reader.readProperty("url") + "/pizzas/new"));
        PageFactory.initElements(webDriver, this);
    }

    public EditPizzaPage selectPizza(String name, String size) {
        pizza_name_field.sendKeys(name);
        pizza_size_field.sendKeys(size);
        create_button.click();
        return new EditPizzaPage(webDriver);

    }

}