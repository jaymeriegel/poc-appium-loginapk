package setup;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestBase {

    protected AndroidDriver<AndroidElement> driver;
    protected DesiredCapabilities capabilities;

    @BeforeSuite
    public void setup() throws IOException {

        FileInputStream filePathProperties = new FileInputStream(System.getProperty("user.dir") + "\\src\\global.properties");
        Properties properties = new Properties();
        properties.load(filePathProperties);

        File appDir = new File("src");
        File app = new File(appDir, (String) properties.get("apk"));
        String device = (String) properties.get("device");
        String plataform = (String) properties.get("plataform");

        this.capabilities = new DesiredCapabilities();
        this.capabilities.setCapability("platformName", plataform);
        this.capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        this.capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        this.capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        this.capabilities.setCapability("appPackage", "com.maxsoftlk.loginapplication");
        this.capabilities.setCapability("appActivity", "com.maxsoftlk.loginapplication.MainActivity");

        this.driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }
}
