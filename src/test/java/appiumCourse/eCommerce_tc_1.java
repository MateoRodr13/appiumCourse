package appiumCourse;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_1 extends BaseTest{

    @Test
    public void FillForm() throws InterruptedException {

        //driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
                //.sendKeys("Mateo Rodriguez");
        //driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale"))
                .click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"))
                .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Colombia\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"))
                .click();
        Thread.sleep(3000);
        String toastMessage =  driver.findElement(By.xpath("//android.widget.Toast [1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }
}
