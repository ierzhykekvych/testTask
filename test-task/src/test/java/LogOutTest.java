import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LogOutTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\86.0.4240.22\\chromedriver.exe";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private static WebDriver driver;

    @DataProvider(name = "logOutTest")
    public Object[][] dataSignIn() {
        return new Object[][] {{("hr.doctor@hospitalrun.io"),("HRt3st12")}};
    }

    @BeforeClass
    private static void setup() {
        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(APPLICATION_URL);
    }

    @Test(dataProvider = "logOutTest")
    private void logOutTest(String userName, String password) {
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        MainPageObject mainPageObject = new MainPageObject(driver);
        signInPageObject.getSingInComponent().inputUsername(userName);
        signInPageObject.getSingInComponent().inputPassword(password);
        signInPageObject.getSingInComponent().clickOnSingInButton();
        mainPageObject.getCommonLeftSideBarComponent().clickOnCogwheelButton();
        mainPageObject.getCommonLeftSideBarComponent().getCogwheelComponent().clickOnLogOutButton();

        String expectedResult = APPLICATION_URL;
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @AfterClass
    private static void tearDown() {
        driver.quit();
    }
}
