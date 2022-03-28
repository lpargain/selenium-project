package com.qa.baseTest;

import com.pageactions.FilterSortingActions;
import com.pageobjects.Locators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    private static final Properties prop = new Properties();
    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    FilterSortingActions filterSortingActions;
    Locators locators;

    public BaseTest() {
        filterSortingActions = new FilterSortingActions(driver);
        locators = new Locators(driver);
    }

    @BeforeMethod
    public void setUpTest() throws IOException {
        InputStream inputStream = new FileInputStream("resource/config.properties");
        prop.load(inputStream);
        setUpFirefox();
        log.info("Initializing browser");
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        quit();
    }

    public void setUpChrome() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void setUpFirefox() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            log.info("quitting browser");
        }
    }
}

