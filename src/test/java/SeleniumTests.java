
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;


public class SeleniumTests {

    WebDriver driver;

    HomePage homePage;
    ABtestPage abTestPage;
    TyposPage typosPage;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
        homePage = PageFactory.initElements(driver, HomePage.class);
        abTestPage = PageFactory.initElements(driver, ABtestPage.class);
        typosPage = PageFactory.initElements(driver, TyposPage.class);
    }

    @Test
    public void testNavigationToABtestPage () {
        homePage.clickOption("A/B Testing");
        Assert.assertEquals(abTestPage.getTitleText(),"A/B Test Control");
    }

    @Test
    public void testTypoPageText (){
        homePage.clickOption("Typos");
        Assert.assertEquals(typosPage.getPageText(), "\n" +
                "Typos\n" +
                "\n" +
                "This example demonstrates a typo being introduced. It does it randomly on each page load.\n" +
                "\n" +
                "Sometimes you'll see a typo, other times you won't.\n");
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
