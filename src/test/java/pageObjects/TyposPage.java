package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TyposPage {

    @FindBy (css = ".example")
    WebElement pageText;

    public String getPageText () {
        return pageText.getText();
    }
}
