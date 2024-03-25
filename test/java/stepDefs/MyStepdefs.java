package stepDefs;

import java.util.Random;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    public WebDriverWait wait;
    public WebDriver driver;
    Random roll = new Random();
    int random;
    private void loadWait() {
        wait = new WebDriverWait(driver, Duration.ofMillis(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".bold:nth-child(1)"))));
    }

    @Given("Navigate to basket login page using {string}")
    public void navigateToBasketLoginPage(String definedWebDriver) {

        if (definedWebDriver.equals("chrome")){
            driver = new ChromeDriver();
        }else if (definedWebDriver.equals("edge")){
            driver = new EdgeDriver();
        }

        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        loadWait();
    }

    @Given("I have entered date of birth {string}")
    public void iHaveEnteredDateOfBirth(String date) {
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys(date);
    }
    @Given("I have entered first name {string} and last name {string}")
    public void iHaveEnteredFirstNameAndLastName(String firstname, String lastname) {
        driver.findElement(By.id("member_firstname")).click();
        driver.findElement(By.id("member_firstname")).sendKeys(firstname);
        driver.findElement(By.id("member_lastname")).click();
        driver.findElement(By.id("member_lastname")).sendKeys(lastname);
    }

    @Given("I have entered a version of email address Johndoe@email.com and confirmed it")
    public void iHaveEnteredEmailAddressAndConfirmedIt() {
        random = roll.nextInt(100);
        String email = "Johndoe" + random + "@email.com";
        driver.findElement(By.name("EmailAddress")).click();
        driver.findElement(By.name("EmailAddress")).sendKeys(email);
        driver.findElement(By.name("ConfirmEmailAddress")).click();
        driver.findElement(By.name("ConfirmEmailAddress")).sendKeys(email);
    }

    @Given("I have entered password {string} and {string} it")
    public void iHaveEnteredPassword(String password, String confirm) {

        driver.findElement(By.id("signupunlicenced_password")).click();
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);

        if (confirm.equals("true")){
            driver.findElement(By.id("signupunlicenced_confirmpassword")).click();
            driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(password);
        }

    }

    @Given("I have checked {string}, age and code of ethics and conduct")
    public void iHaveCheckedAgeAndCodeOfEthicsAndConduct(String tac) {
        if (tac.equals("true")){
            driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        }
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
    }

    @When("I click the confirm and join button")
    public void iClickTheButton() {
        driver.findElement(By.name("join")).click();
    }

    @Then("I verify {string} on the site")
    public void iVerifyOnTheSite(String text) {
        String expected = text;

        if (text.equals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND")){
            String actual = (driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText());
            assertEquals(expected, actual);

        }else if (text.equals("Last Name is required")){
            String actual = (driver.findElement(By.cssSelector(".row:nth-child(6) .warning > span")).getText());
            assertEquals(expected, actual);

        }else if (text.equals("Confirm Password is required")){
            String actual = (driver.findElement(By.cssSelector(".row:nth-child(2) > .col-sm-6:nth-child(2) .warning > span")).getText());
            assertEquals(expected, actual);

        } else {
            String actual = (driver.findElement(By.cssSelector(".md-checkbox:nth-child(1) > .warning > span")).getText());
            assertEquals(expected, actual);
        }
    }
}
