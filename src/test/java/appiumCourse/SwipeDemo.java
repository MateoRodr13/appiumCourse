package appiumCourse;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeDemo extends BaseTest{

    @Test
    public void SwipeDemoTest() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views"))
                .click();
        driver.findElement(AppiumBy.accessibilityId("Gallery"))
                .click();
        driver.findElements(AppiumBy.className("android.widget.TextView")).get(1)
                .click();

        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");

        //Swipe
        swipeAction(firstImage, "left");
        Thread.sleep(2000);


        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");

    }

}
