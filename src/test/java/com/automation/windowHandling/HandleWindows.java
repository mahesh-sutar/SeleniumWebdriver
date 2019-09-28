package com.automation.windowHandling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HandleWindows {

    WebDriver driver;

    @Test
    public void testHandleWindows() {
        System.setProperty("webdriver.chrome.driver", "D:\\WorkSpace\\dev\\SeleniumWebdriver\\drivers\\chromedriver_77.exe");
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Get parent window id
        String strParentWinID = driver.getWindowHandle();

        //Click on link which opens new winow
        //This link is inside an iFrame so we need to switch to frame first
        WebElement frameID = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(frameID);
        WebElement link = driver.findElement(By.xpath("/html/body/button"));
        link.click();

        //Get ids of all window opened by webdriver, It will returns set of strings and all are unique ids
        Set<String> allWinID = driver.getWindowHandles();

        //Go through all window IDs and compare each with parent window ID
        Iterator<String> iterator = allWinID.iterator();

        while (iterator.hasNext()) {
            String strChildWinID = iterator.next();

            //Compare strChildWinID with strParentWinID, if both are not equal then do switch window to chld window
            if (!strParentWinID.equalsIgnoreCase(strChildWinID)) {
                driver.switchTo().window(strChildWinID);

                //Perform operations on child window
                WebElement cssExampleText = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/h3"));
                String strActualText = cssExampleText.getText();
                Assert.assertEquals(strActualText, "CSS Example:");
            }
        }

        //Close all browser window
        driver.quit();

    }
}
