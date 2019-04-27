package ru.ac.uniyar.testingcourse.selenium;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper {

    private static Random random = new Random();

    public static void authorize(WebDriver driver) {
        driver.get("https://yar.fruct.org/login");
        driver.findElement(By.id("username")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys("testtest");
        driver.findElement(By.id("password")).submit();
    }

    public static String generateTaskName() {
        return "Тестовая задача " + random.nextInt(1000000);
    }

}
