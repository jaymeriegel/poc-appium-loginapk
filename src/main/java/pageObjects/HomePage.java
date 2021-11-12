package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage {

    private final By greetingLabel = By.id("com.maxsoftlk.loginapplication:id/tvGreeting");
    private final By welcomeLabel = By.id("com.maxsoftlk.loginapplication:id/tvWelcome");

    private final AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getGreetingLabelText() {
        return driver.findElement(greetingLabel).getText();
    }

    public String getWelcomeLabelText() {
        return driver.findElement(welcomeLabel).getText();
    }

}
