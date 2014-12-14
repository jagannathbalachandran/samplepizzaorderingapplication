package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import entity.Pizza;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import util.PropertyReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SharedContext

{
    WebDriver webDriver;
    private Pizza pizza;

    @Before
    public void beforeScenario()  {
        webDriver = setAppropriateDriver();
        webDriver.manage().timeouts().pageLoadTimeout(30 , TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(2 , TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    private WebDriver setAppropriateDriver()  {
        PropertyReader reader = new PropertyReader();
        String browser = reader.readProperty("browser");
       if(browser.equalsIgnoreCase("chrome")) {
           System.out.println("PATH is " + System.getenv("PATH"));
               if (System.getenv("PATH").equalsIgnoreCase("chromedriver")) {
                   webDriver = new ChromeDriver();
               }
               else
               {
                   System.out.println("Chromedriver NOT present in system path. Please refer https://code.google.com/p/selenium/wiki/ChromeDriver");
                   System.exit(0);
               }
       }

       else
           if(browser.equalsIgnoreCase("safari")) webDriver = new SafariDriver();
        else
            webDriver = new FirefoxDriver();

        return webDriver;
    }

    @After
    public void afterScenario(Scenario scenario){
        try
        {
            if(scenario.isFailed()){
                final byte[] screenshot =  ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot , "image/png");
            }
        }
        catch(WebDriverException exception){
            System.out.println("Exception while capturing screenshot for scenario " +  scenario);
        }

        webDriver.quit();
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }
}
