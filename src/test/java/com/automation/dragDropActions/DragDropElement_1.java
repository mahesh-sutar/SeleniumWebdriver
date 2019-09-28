package com.automation.dragDropActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DragDropElement_1 {
    WebDriver driver;

    @Test
    public void testDragDropElement() {
        System.setProperty("webdriver.chrome.driver", "D:\\WorkSpace\\dev\\SeleniumWebdriver\\drivers\\chromedriver_77.exe");
        driver = new ChromeDriver();
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Using Actions class we can perform drag and drop element
        Actions action = new Actions(driver);

        //Both droppable and draggable elements are inside iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        WebElement sourceElement = driver.findElement(By.id("draggable"));
        WebElement destinationElement = driver.findElement(By.id("droppable"));

        //To verify dragdrop action : Text before dropping element
        String strBeforeDrop = destinationElement.getText();
        System.out.println("Before Drop : "+strBeforeDrop);

        //Performing drag and drop action
        action.dragAndDrop(sourceElement,destinationElement).build().perform();

        //To verify dragdrop action : Text after dropping element.
        String strAfterDrop = destinationElement.getText();
        System.out.println("After Drop : "+strAfterDrop);

        //If both are not same then drag drop performed successfully
        Assert.assertNotEquals(strBeforeDrop,strAfterDrop);
    }
}
