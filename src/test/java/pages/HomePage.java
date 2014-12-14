package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    private WebDriver webDriver;
    @FindBy(linkText = "Order a pizza")
    private WebElement order_pizza_link;



    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver , this);
    }

    public void navigateToOrderPage() {
        order_pizza_link.click();
    }
}
