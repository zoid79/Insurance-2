//package com.mju.shop.customer.controller;
//
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNot.not;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@SpringBootTest
//public class CustomerSelenium {
//    private Map<String, Object> vars;
//    private static WebDriver driver;
//    private static String baseUrl;
//    private static JavascriptExecutor js;
//    private static WebDriverWait wait;
//
//    @Before
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
//        baseUrl = "http://localhost:8090";
//        driver = new ChromeDriver(); // Driver 생성
//        driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, 20);
//        js = (JavascriptExecutor) driver;
//    }
//
//    @After
//    public void tearDown() {
//        // driver.quit();
//    }
//
//    @Test
//    public void customer() {
//        driver.get("http://localhost:8090/");
//        driver.manage().window().setSize(new Dimension(1792, 1095));
//
//        // login
//        driver.findElement(By.linkText("LOGIN")).click();
//        driver.findElement(By.id("inputId")).click();
//        driver.findElement(By.id("inputId")).sendKeys("yeojin");
//        driver.findElement(By.id("inputPassword")).click();
//        driver.findElement(By.id("inputPassword")).sendKeys("1234");
//        driver.findElement(By.cssSelector(".col-12:nth-child(1) > .btn")).click();
//        pauseSelenium(1000);
//    }
//
//    @Test
//    public void signuptest() {
//        driver.get("http://localhost:8090/");
//        driver.manage().window().setSize(new Dimension(1792, 1095));
//        driver.findElement(By.linkText("JOIN")).click();
//        driver.findElement(By.id("userId")).click();
//        driver.findElement(By.id("userId")).sendKeys("ask03142");
//        {
//            WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(4)"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).clickAndHold().perform();
//        }
//        {
//            WebElement element = driver.findElement(By.cssSelector(".card-body"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).release().perform();
//        }
//        driver.findElement(By.cssSelector(".card-body")).click();
//        driver.findElement(By.id("userPassword")).click();
//        driver.findElement(By.id("userPassword")).click();
//        {
//            WebElement element = driver.findElement(By.id("userPassword"));
//            Actions builder = new Actions(driver);
//            builder.doubleClick(element).perform();
//        }
//        driver.findElement(By.id("userPassword")).click();
//        driver.findElement(By.id("userPassword")).sendKeys("Qazwsx1018!");
//        driver.findElement(By.id("userPassword_repeat")).click();
//        driver.findElement(By.id("userPassword_repeat")).sendKeys("Qazwsx1018!");
//        driver.findElement(By.id("userName")).click();
//        driver.findElement(By.id("userName")).click();
//        {
//            WebElement element = driver.findElement(By.id("userName"));
//            Actions builder = new Actions(driver);
//            builder.doubleClick(element).perform();
//        }
//        driver.findElement(By.id("userName")).sendKeys("김형균");
//        driver.findElement(By.cssSelector(".container > .row")).click();
//        driver.findElement(By.id("userEmail")).click();
//        driver.findElement(By.id("userEmail")).sendKeys("ask03142@naver.com");
//        {
//            WebElement element = driver.findElement(By.id("userPhoneNumber"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).clickAndHold().perform();
//        }
//        {
//            WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(8)"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).release().perform();
//        }
//        driver.findElement(By.cssSelector(".form-group:nth-child(8)")).click();
//        driver.findElement(By.id("userPhoneNumber")).sendKeys("010-9019-5201");
//        driver.findElement(By.cssSelector(".btn")).click();
//        assertThat(driver.switchTo().alert().getText(), is("회원가입이 완료되었습니다."));
//        driver.findElement(By.id("inputId")).sendKeys("김형균");
//        driver.findElement(By.id("inputPassword")).sendKeys("Qazwsx1018!");
//        driver.findElement(By.id("inputId")).click();
//        driver.findElement(By.id("inputId")).click();
//        {
//            WebElement element = driver.findElement(By.id("inputId"));
//            Actions builder = new Actions(driver);
//            builder.doubleClick(element).perform();
//        }
//        driver.findElement(By.id("inputId")).sendKeys("ask03142");
//        driver.findElement(By.cssSelector(".col-12:nth-child(1) > .btn")).click();
//    }
//
//    public void pauseSelenium(Integer milliseconds) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
