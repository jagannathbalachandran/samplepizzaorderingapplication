package pages;

import entity.Topping;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;


public class OrderPage {

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

    @FindBy(linkText = "Add toppings")
    private WebElement add_topping_button;

    @FindBy(linkText = "Order this pizza")
    private WebElement order_pizza_link;

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void selectPizza(String name, String size) {
        pizza_name_field.sendKeys(name);
        pizza_size_field.sendKeys(size);
        create_button.click();

    }

    private void addTopping(Topping topping) {
        add_topping_button.click();
        topping_name_field.sendKeys(topping.getName());
        if(topping.isDoubleOrder())   topping_double_order_field.click();
        create_button.click();
    }

    public void order() {
        order_pizza_link.click();    }

    public void addToppings(List<Topping> toppings) {
        if(toppings.isEmpty()) return;
        for (Iterator<Topping> iterator = toppings.iterator(); iterator.hasNext(); ) {
                Topping topping = iterator.next();
                addTopping(topping);
            }
        }
    }
