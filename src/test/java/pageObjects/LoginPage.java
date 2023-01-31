package pageObjects;

import common.Configuration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class LoginPage {
    WebDriver driver;
    @BeforeMethod
    public void starUpBrowser(){
       System.setProperty("webdriver.chrome.driver", Configuration.CHROME_DRIVER_DIR);
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/login");
    }
    @Test
    public void loginForm() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();
        Thread.sleep(3000);
        String loginTextConfirmation = driver.findElement(By.className("subheader")).getText();
        String logoutBtn = driver.findElement(By.partialLinkText("Logout")).getText();
        SoftAssert loginPageConfirm = new SoftAssert();
        loginPageConfirm.assertEquals(loginPageConfirm,"Welcome to the Secure Area. When you are done click logout below.","Login confirmation was successfull");
        loginPageConfirm.assertEquals(logoutBtn,"Logout","Login confirmation was successfull");
        driver.findElement(By.partialLinkText("Logout")).click();
        driver.findElement(By.cssSelector("#login > button")).isDisplayed();
        Thread.sleep(3000);
    }

    @Test
    public void loginInvalid(){
        driver.findElement(By.name("username")).sendKeys("Nottomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperNoSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();
        String invalidText = driver.findElement(By.id("flash")).getText();
        SoftAssert loginPageValid = new SoftAssert();
        loginPageValid.assertEquals(invalidText, "Your username is invalid!", "Invalid username and password validation");
        //System.out.println(invalidText);
    }

    @Test
    public void loginInvalidPassword(){
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperNoSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();
        String invalidText = driver.findElement(By.id("flash")).getText();
        SoftAssert loginPageValid = new SoftAssert();
        loginPageValid.assertEquals(invalidText, "Your password is invalid!", "Invalid password validation");
        //System.out.println(invalidText);
    }

    @Test
    public void loginInvalidUser(){
        driver.findElement(By.name("username")).sendKeys("Nottomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("#login > button")).click();
        String invalidText = driver.findElement(By.id("flash")).getText();
        SoftAssert loginPageValid = new SoftAssert();
        loginPageValid.assertEquals(invalidText, "Your username is invalid!", "Invalid username validation");
        //System.out.println(invalidText);
    }

    @AfterMethod
    public void closeBrowser(){
        System.out.println("Closing down the browser");
      driver.close();
    }
}
