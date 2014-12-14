package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.PropertyReader;
import static junit.framework.Assert.assertTrue;


public class HomePage {
    private WebDriver webDriver;

    @FindBy(linkText = "Order a pizza")
    private WebElement order_pizza_link;

    @FindBy(linkText = "Show ordered pizzas")
    private WebElement list_of_orders_link;

    @FindBy(linkText = "Edit")
    private WebElement edit_profile_link;

    PropertyReader reader;


    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        reader = new PropertyReader();
        assertTrue(webDriver.getCurrentUrl().equals(reader.readProperty("url") + "/account"));
        PageFactory.initElements(webDriver , this);
    }

    public NewPizzaPage navigateToOrderPage() {
        order_pizza_link.click();
        return new NewPizzaPage((webDriver));
    }

    public OrderListingPage navigateToOrderListingPage() {
        webDriver.navigate().to(reader.readProperty("url") + "/account");
        list_of_orders_link.click();
        return new OrderListingPage(webDriver);
    }
}
