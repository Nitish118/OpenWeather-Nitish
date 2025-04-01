package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        driver.get("https://openweathermap.org/");
        
        driver.findElement(By.linkText("Sign in")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        
        // Fill in the login credentials
        driver.findElement(By.id("user_email")).sendKeys("test");
        driver.findElement(By.id("user_password")).sendKeys("test");
        driver.findElement(By.name("commit")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Dashboard"))); 
        assertTrue(driver.getPageSource().contains("Dashboard") || driver.getPageSource().contains("Logout"), "Login failed!");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
