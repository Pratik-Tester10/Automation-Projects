package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    private Wrappers wrappers;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @Test
    public void testCase01() throws InterruptedException {

        System.out.println("START : testCase01 is Started" + "\n");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        wrappers.sendKeys(By.xpath("(//*[@class='whsOnd zHQkBf'])[1]"), "Crio Learner");
        System.out.println("Pass : Name is Printed");

        long epoch = System.currentTimeMillis() / 1000;

        wrappers.sendKeys(By.xpath("//*[@class='KHxj8b tL9Q4c']"), "I want to be the best QA Engineer! " + epoch);
        System.out.println("Pass : Why are you practicing Automation is Printed");
        Thread.sleep(500);

        String exp = "0 - 2"; // 3 - 5 / 6 - 10 / > 10
        wrappers.click(By
                .xpath("//*[text()='" + exp + "']/parent::div/parent::div//preceding-sibling::div/div[@role='radio']"));
        System.out.println("Pass : Experience is selected");

        String skill_1 = "Java", skill_2 = "Selenium", skill_3 = "Springboot", skill_4 = "TestNG";

        wrappers.click(By.xpath(
                "//*[text()='" + skill_1 + "']/parent::div/parent::div//preceding-sibling::div[@role='checkbox']"));
        Thread.sleep(500);
        wrappers.click(By.xpath(
                "//*[text()='" + skill_2 + "']/parent::div/parent::div//preceding-sibling::div[@role='checkbox']"));
        Thread.sleep(500);
        wrappers.click(By.xpath(
                "//*[text()='" + skill_4 + "']/parent::div/parent::div//preceding-sibling::div[@role='checkbox']"));
        Thread.sleep(500);
        System.out.println("Pass : Skills are selected");

        String prefix = "Mr"; // Ms / Mrs / Dr / Rather not say
        wrappers.click(By.xpath("//*[@role='option']"));
        Thread.sleep(500);
        wrappers.click(By.xpath("//*[@data-value='" + prefix + "' and @role='option']"));
        System.out.println("Pass : Prefix is selected");
        Thread.sleep(500);

        wrappers.sendKeys(By.xpath("//*[@type='date']"), wrappers.date7DaysAgo());
        System.out.println("Pass : 7 days earlier date is printed");
        Thread.sleep(500);

        String time = wrappers.currentTime();
        char[] arr = time.toCharArray();
        StringBuilder hr = new StringBuilder();
        StringBuilder mn = new StringBuilder();
        hr.append(arr[0]);
        hr.append(arr[1]);
        mn.append(arr[3]);
        mn.append(arr[4]);
        String hrs = hr.toString();
        String mns = mn.toString();

        wrappers.sendKeys(By.xpath("//*[@aria-label='Hour']"), hrs);
        Thread.sleep(500);
        wrappers.sendKeys(By.xpath("//*[@aria-label='Minute']"), mns);
        System.out.println("Pass : current time is printed");
        Thread.sleep(500);

        wrappers.click(By.xpath("//*[@role='button' and @jsname='M2UYVd']"));
        System.out.println("Pass : Clicked on submit button");
        Thread.sleep(1000);

        System.out.println("Pass : Form submitted Successfully. Success message printed : "
                + wrappers.getText(By.xpath("//*[@class='vHW8K']")));
        System.out.println("Pass : Form submitted Successfully and success message printed" + "\n");
        
        System.out.println("END : testCase01 is Ended");

    }

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform?pli=1");

        wrappers = new Wrappers(driver, 10);
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();
    }
}