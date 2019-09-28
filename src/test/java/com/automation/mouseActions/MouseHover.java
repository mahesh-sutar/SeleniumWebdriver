package com.automation.mouseActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MouseHover {
    WebDriver driver;

    @Test
    public void testMouseHoverAction() {
        System.setProperty("webdriver.chrome.driver", "D:\\WorkSpace\\dev\\SeleniumWebdriver\\drivers\\chromedriver_77.exe");
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/cssref/tryit.asp?filename=trycss_sel_hover");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Using Actions class we can perform mouse hover action
        Actions actions = new Actions(driver);

        //Element is inside iFrame
        driver.switchTo().frame(driver.findElement(By.id("iframeResult")));

        //Element on which we want to perform mouse hover
        WebElement element = driver.findElement(By.xpath("/html/body/a[1]"));

        //Verify mouse hover : Before performing mouse hover text background color should be yellow
        String strBeforeBackColor = element.getCssValue("background-color");
        System.out.println("Element background color after mouse hover : " + strBeforeBackColor);

        //Perform mouse hover action
        actions.moveToElement(element).build().perform();

        //Verify mouse hover : After performing mouse hover text background color should be yellow
        String strAfterBackColor = element.getCssValue("background-color");
        System.out.println("Element background color after mouse hover : " + strAfterBackColor);

        //If true then mouse hover performed successfully
        Assert.assertNotEquals(strBeforeBackColor, strAfterBackColor);
    }
}
