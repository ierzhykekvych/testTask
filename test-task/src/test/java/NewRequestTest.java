import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

public class NewRequestTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\87.0.4280.88\\chromedriver.exe";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private static WebDriver driver;
    private WebDriverWait wait;
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
    private void logOutTest(String userName, String password) throws InterruptedException, ParseException {
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        MainPageObject mainPageObject = new MainPageObject(driver);
        MedicationPageObject medicationPageObject = new MedicationPageObject(driver);
        NewMedicationRequestPageObject newMedicationRequestPageObject = new NewMedicationRequestPageObject(driver);
        Prescription prescription = new Prescription();
        NameMedicationPropertiesList nameMedicationPropertiesList = new NameMedicationPropertiesList();
        signInPageObject.getSingInComponent().inputUsername(userName);
        signInPageObject.getSingInComponent().inputPassword(password);
        signInPageObject.getSingInComponent().clickOnSingInButton();
        mainPageObject.getCommonLeftSideBarComponent().clickOnMedicationButton();
        //medicationPageObject.getMedicationPropertiesList().clickNewRequest();
        /*
        newMedicationRequestPageObject.inputPatient("Test Patient");
        medicationPageObject.getMedicationPropertiesList().clickNewRequest();
         */

        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().requestButton(), nameMedicationPropertiesList.requests());
        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().newRequestButton(), nameMedicationPropertiesList.newRequest());
        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().returnMedicationButton(), nameMedicationPropertiesList.returnMedication());
        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().completedButton(), nameMedicationPropertiesList.completed());

        medicationPageObject.getMedicationPropertiesList().clickNewRequest();
        //newMedicationRequestPageObject.inputPatient("Test Patient");
        Math.random();


        Thread.sleep(1000);
        newMedicationRequestPageObject.inputPatient(" ");
        Thread.sleep(1000);
        newMedicationRequestPageObject.inputPatient("Pat");
        Thread.sleep(1000);
        newMedicationRequestPageObject.inputPatient("ie");
        Thread.sleep(1000);
        newMedicationRequestPageObject.inputPatient("nt");
        newMedicationRequestPageObject.selectPatient();
        newMedicationRequestPageObject.clickOnVisitButton();
        Thread.sleep(2000);
        newMedicationRequestPageObject.selectVisitOfData();
        Thread.sleep(2000);
        newMedicationRequestPageObject.inputMedication("Pramoxine");
        Thread.sleep(2000);
        newMedicationRequestPageObject.selectMedication();
        newMedicationRequestPageObject.inputPrescription(prescription.Prescription);
        newMedicationRequestPageObject.inputPrescriptionDate();
        newMedicationRequestPageObject.inputRefills();
        newMedicationRequestPageObject.inputQuantityRequested();

        newMedicationRequestPageObject.clickOnAddButton();

        Assert.assertTrue(newMedicationRequestPageObject.getMedicationRequestComponent().isVisibleCrossButtonButton());
        Assert.assertTrue(newMedicationRequestPageObject.getMedicationRequestComponent().isVisibleIsCrossButton());

        newMedicationRequestPageObject.getMedicationRequestComponent().clickOnOkButton();
        Thread.sleep(3000);
        Assert.assertTrue(newMedicationRequestPageObject.isVisibleMedicationRequestSaved());
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(),"http://demo.hospitalrun.io/#/medication/edit/new");
        // Find and click on a random product
        //Thread.sleep(2000);



        //newMedicationRequestPageObject.selectVisit();
        //Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().newRequestButton(), nameMedicationPropertiesList.name());
        //ArrayList<String> expected = nameMedicationPropertiesList.name();
        //ArrayList<String> actual = medicationPageObject.getMedicationPropertiesList().optionsList();
        //String expectedResult = APPLICATION_URL;
        //String actualResult = driver.getCurrentUrl();

        //Assert.assertEquals(actual, expected);
    }

    @AfterClass
    private static void tearDown() {
        driver.quit();
    }

}
