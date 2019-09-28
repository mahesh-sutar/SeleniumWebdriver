package com.automation.dynamicWebTable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTableHandle {

WebDriver driver;

    @Test
    public void testDynamicWebTable()
    {
        System.setProperty("webdriver.chrome.driver", "D:\\WorkSpace\\dev\\SeleniumWebdriver\\drivers\\chromedriver_77.exe");
        driver = new ChromeDriver();
        driver.get("http://www.money.rediff.com/gainers/bsc/daily/groupa?");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Using Xpath to locate Rows and Columns
        List<WebElement> cols = driver.findElements(By.xpath("//*[@id='leftcontainer']/table/thead/tr/th"));
        System.out.println("Number of columns in table : "+cols.size());

        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
        System.out.println("Number of rows in table : "+rows.size());

        //Data in table is continuously changing but our code will remain same to retrive specific record
        //To get the data from 3rd row and 2nd column
        //To find third row
        WebElement tableRow = driver.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr[3]/td[2]"));

        String strTableRowText = tableRow.getText();

        System.out.println("Table cell value : "+strTableRowText);
    }
}
