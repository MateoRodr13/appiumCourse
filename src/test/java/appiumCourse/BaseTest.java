package appiumCourse;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {
        //Start server appium
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Asus\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        //AndroidDriver, IOSDriver
        //Appium code -> Appium server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("MateoTest");//Emulador
        //options.setDeviceName("DI59BA6HJBLB4LDU");//Celular
        //options.setApp("C:\\Users\\Asus\\Documents\\OpiTech\\AppiumCourse\\appiumCourse\\src\\test\\java\\resources\\application-5c4de184-40ea-4db5-b361-c57ef944e2a2.apk");
        options.setApp("C:\\Users\\Asus\\Documents\\OpiTech\\AppiumCourse\\appiumCourse\\src\\test\\java\\resources\\ApiDemos-debug.apk");
        //options.setApp("C:\\Users\\Asus\\Documents\\OpiTech\\AppiumCourse\\appiumCourse\\src\\test\\java\\resources\\General-Store.apk");

         driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    public void longPressAction(WebElement elementId){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)elementId).getId(),
                        "duration", 2000));
    }

    public void scrollToEndAction(){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        }while(canScrollMore);
    }

    public void swipeAction(WebElement elementId, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)elementId).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    @AfterClass
    public void tearDown(){
        //Stop driver & server appium
        driver.quit();
        service.stop();
    }
}
