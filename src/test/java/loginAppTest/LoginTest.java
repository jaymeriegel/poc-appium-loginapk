package loginAppTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import setup.TestBase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {

    private LoginPage loginPage;

    @BeforeMethod
    public void spinUpAndroidDriver() {
        try {
            driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), this.capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify that a user can login to the application with valid credentials")
    public void testValidLogin() {
        loginPage.login("Osanda", "MaxSoft123");
        HomePage homePage = new HomePage(this.driver);
        Assert.assertEquals(homePage.getGreetingLabelText(), "Hi Osanda,");
        Assert.assertEquals(homePage.getWelcomeLabelText(), "Welcome!");
    }

    @Test(description = "Verify that a user cannot login to the application with invalid credentials")
    public void testInvalidLogin() {
        loginPage.login("Osanda", "MaxSoft1234");
        Assert.assertEquals(loginPage.getAttemptsCounterLabelText(), "Number of attempts remaining: 4");
    }

    @AfterMethod
    public void stopAndroidDriver() {
        driver.quit();
    }

}
