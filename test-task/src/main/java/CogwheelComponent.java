import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CogwheelComponent extends CommonPageObject {

    private static final String LOG_OUT_BUTTON = "//*[text()=\"Logout\"]";

    @FindBy(how = How.XPATH, using = LOG_OUT_BUTTON)
    private WebElement logOutButton;

    public CogwheelComponent(WebDriver driver, WebElement element) {
        super(driver);
    }

    public void clickOnLogOutButton() {
        logOutButton.click();
    }
}