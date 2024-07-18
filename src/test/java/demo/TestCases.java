package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class TestCases {
    ChromeDriver driver;
    private Wrappers wrapper;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */

    @Test(priority = 1)
    public void testCase01() throws InterruptedException {
        System.out.println("\n" + "START : Started testCase01");
        driver.get("https://www.flipkart.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        wrapper.sendKeys(By.xpath("//*[@name='q']"), "Washing Machine");
        wrapper.hitEnter(By.xpath("//*[@name='q']"));
        System.out.println("Pass : Product search Successfull");
        Thread.sleep(500);

        wrapper.click(By.xpath("//*[text()='Popularity']"));
        System.out.println("Pass : Sorted by popularity Successfull");
        Thread.sleep(1000);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String Star = "4";
        WebElement Ratings = driver
                .findElement(By.xpath("//*[contains(text(),'" + Star + "') and contains(text(),'above')]"));
        jse.executeScript("arguments[0].scrollIntoView", Ratings);
        Thread.sleep(500);
        wrapper.click(By.xpath("//*[contains(text(),'" + Star
                + "') and contains(text(),'above')]/parent::label/div[@class='XqNaEv']"));
        System.out.println("Pass : 4★ & above filter applied Successfull");
        Thread.sleep(500);

        List<WebElement> products = driver.findElements(By.className("KzDlHZ"));
        int productCount = products.size();
        System.out.println("Pass : Products above rating 4★ Counted Successfully. Count : " + productCount);

        System.out.println("END : Ended testCase01" + "\n");
    }

    @Test(priority = 2)
    public void testCase02() throws InterruptedException {
        System.out.println("\n" + "START : Started testCase02");
        driver.get("https://www.flipkart.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        wrapper.sendKeys(By.xpath("//*[@name='q']"), "iPhone");
        wrapper.hitEnter(By.xpath("//*[@name='q']"));
        System.out.println("Pass : Product search Successfull");
        Thread.sleep(500);

        List<WebElement> parentElements = driver.findElements(By.xpath("//div[@class='UkUFwK']/span"));
        for (WebElement elem : parentElements) {
            String discounts = elem.getText();
            char[] arr = discounts.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != '%') {
                    sb.append(arr[i]);
                } else {
                    break;
                }
            }
            String discount = sb.toString();
            if (Integer.valueOf(discount) > 17) {
                WebElement prod = elem.findElement(By.xpath(
                        "./parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/div[@class='KzDlHZ']"));
                System.out.println("Product Name : " + prod.getText() + ", Discount is : " + discount + "%.");
            }
        }
        System.out.println("Pass : Title and discount of iPhones having discount more than 17%, Printed Successfully");

        System.out.println("END : Ended testCase02" + "\n");
    }

    @Test(priority = 3)
    public void testCase03() throws InterruptedException {
        System.out.println("\n" + "START : Started testCase03");
        driver.get("https://www.flipkart.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        wrapper.sendKeys(By.xpath("//*[@name='q']"), "Coffee Mug");
        wrapper.hitEnter(By.xpath("//*[@name='q']"));
        System.out.println("Pass : Product search Successfull");
        Thread.sleep(1000);

        String Star = "4";
        wrapper.click(By.xpath("//*[contains(text(),'" + Star
                + "') and contains(text(),'above')]/parent::label/div[@class='XqNaEv']"));
        System.out.println("Pass : 4 Star & above filter applied Successfull");
        Thread.sleep(1000);

        List<WebElement> items = driver.findElements(By.xpath("//*[@class='Wphh3N']"));
        List<Item> itemList = new ArrayList<>();

        for (WebElement item : items) {
            String title = item.findElement(By.xpath("./parent::div/parent::div/a[@class='wjcEIp']")).getText();
            String imageUrl = item.findElement(By.xpath("./parent::div/preceding-sibling::a/div/div/div/img")).getAttribute("src");
            String reviewsText = item.findElement(By.xpath("./parent::div/span[@class='Wphh3N']")).getText();
            int reviewCount = extractReviewCount(reviewsText);

            itemList.add(new Item(title, imageUrl, reviewCount));
        }

        Collections.sort(itemList, Comparator.comparingInt(Item::getReviewCount).reversed());

        // Print the title and image URL of the top 5 items
        for (int i = 0; i < Math.min(5, itemList.size()); i++) {
            Item item = itemList.get(i);
            System.out.println("Title: " + item.getTitle());
            System.out.println("Image URL: " + item.getImageUrl());
            System.out.println("Review Count: " + item.getReviewCount());
            System.out.println();
        }
        System.out.println("Pass : 5 items with Highest number of reviews Printed Successfully");

        System.out.println("END : Ended testCase03" + "\n");

    }

    private int extractReviewCount(String reviewsText) {
        // Remove commas, parentheses, and whitespace
        String cleanedText = reviewsText.replace(",", "").replace("(", "").replace(")", "").trim();
        return Integer.parseInt(cleanedText);
    }

    public static class Item {
        private final String title;
        private final String imageUrl;
        private final int reviewCount;

        public Item(String title, String imageUrl, int reviewCount) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.reviewCount = reviewCount;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getReviewCount() {
            return reviewCount;
        }
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

        wrapper = new Wrappers(driver, 10);
    }

    @AfterTest
    public void endTest() {  
        driver.close();
        driver.quit();
    }
}