package ru.ac.uniyar.testingcourse.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleTestSuite {

    private WebDriver driver;

    public SimpleTestSuite() {
    }

    @Before
    public void initializeDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void finalizeDriver() {
        driver.quit();
    }

    @Test
    public void projectsLinkLeadsToProjectsPage() throws InterruptedException {
        driver.get("http://yar.fruct.org");
        WebElement projectsLink = driver.findElement(By.linkText("Projects"));
        projectsLink.click();
        assertThat(driver.getTitle()).contains("Projects");
    }

    @Test
    public void userWithValidCredentialsCanLogin() {
        driver.get("https://yar.fruct.org/login");
        driver.findElement(By.id("username")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys("testtest");
        driver.findElement(By.id("password")).submit();
        assertThat(driver.findElement(By.tagName("body")).getText()).contains("Logged in as test");
    }

    @Test
    public void userSeesPrivateProjectAfterAuthorization() {
        driver.get("https://yar.fruct.org/projects");
        WebElement body = driver.findElement(By.tagName("body"));
        assertThat(body.getText()).doesNotContain("Selenium Tests Project");

        TestHelper.authorize(driver);

        driver.get("https://yar.fruct.org/projects");
        body = driver.findElement(By.tagName("body"));
        assertThat(body.getText()).contains("Selenium Tests Project");
    }

}
