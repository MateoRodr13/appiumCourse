package appiumCourse;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class eCommerce_tc_3 extends BaseTest{

    @Test
    public void FillForm() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
                .sendKeys("Mateo Rodriguez");
        driver.hideKeyboard();
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

        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));

        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int productCount = productPrices.size();
        double totalPrice = 0;
        for (int i = 0; i < productCount; i++) {
            String amountString = productPrices.get(i).getText();
            totalPrice += getFormattedAmount(amountString);
        }

        String totalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Assert.assertEquals(getFormattedAmount(totalAmount), totalPrice);

        longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(2000);
    }
}
