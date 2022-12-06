package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class LoginPage {
    WebDriver driver;
    @BeforeTest
    public void starUpBrowser(){
       System.setProperty("webdriver.chrome.driver","driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/login");
    }
    @Test
    public void loginForm(){
        System.out.println("wenas");
    }
    @AfterTest
    public void closeBrowser(){
        System.out.println("Closing down the browser");
        driver.close();
    }
}
