package ru.ac.uniyar.testingcourse.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizedUserTestSuite {

    private static WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new ChromeDriver();
        TestHelper.authorize(driver);
    }

    @After
    public void finalizeDriver() {
        driver.quit();
    }

    public AuthorizedUserTestSuite() {
    }

    @Test
    public void addIssueUsingNewIssueButton() {
        driver.get("https://yar.fruct.org/projects/selenium-tests-project/issues");
        WebElement newIssueLink = driver.findElement(By.className("new-issue"));
        assertThat(newIssueLink.getText()).isEqualTo("New issue");
        newIssueLink.click();
        assertThat(driver.getTitle()).contains("New issue");

        String taskName = TestHelper.generateTaskName();
        driver.findElement(By.cssSelector("input#issue_subject")).sendKeys(taskName);
        driver.findElement(By.xpath("//input[@value='Create']")).click();
        assertThat(driver.getTitle()).contains(taskName);
    }

    @Test
    public void openNewIssueWindowViaPopupMenu() {
        driver.get("https://yar.fruct.org/projects/selenium-tests-project/issues");
        WebElement plusButton = driver.findElement(By.id("new-object"));
        WebElement newIssueItem = driver.findElement(By.className("new-issue-sub"));
        plusButton.click();
        newIssueItem.click();
        assertThat(driver.getTitle()).contains("New issue");
    }

}
