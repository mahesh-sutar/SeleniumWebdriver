package com.automation.alertHandling;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AlertPopUpHandle {

    WebDriver driver;

    @Test
    public void testAlertPopUpHandle() {

        System.setProperty("webdriver.chrome.driver", "D:\\WorkSpace\\dev\\SeleniumWebdriver\\drivers\\chromedriver_77.exe");
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Try it button is inside iframe, need to switch to frame
        WebElement iframe = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframe);

        //Click on 'Try it' button which opens alert
        WebElement btnTryIt = driver.findElement(By.xpath("/html/body/button"));
        btnTryIt.click();

        Alert alert = driver.switchTo().alert();

        //Get the message from alert
        String expectedAlertMsg = "I am an alert box!";
        String actualAlertMsg = alert.getText();

        Assert.assertEquals(actualAlertMsg, expectedAlertMsg);

        //click on Ok button
        alert.accept();
    }
}
