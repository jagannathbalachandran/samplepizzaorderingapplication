package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import util.PropertyReader;

public class LoginSteps {

    private WebDriver webDriver;
    private LoginPage loginPage;
    private HomePage homePage;
    PropertyReader reader;

    public LoginSteps(SharedContext driver){
        webDriver = driver.getDriver();
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        reader = new PropertyReader();
    }

    @Given("^I am on login page$")
    public void the_user_is_on_login_page() throws Throwable {
          loginPage.navigateToLoginPage(reader.readProperty("url"));
    }

    @When("^I sign in$")
    public void the_user_signs_in() throws Throwable {
        loginPage.signInAs( reader.readProperty("username"),  reader.readProperty("password"));
    }
}
