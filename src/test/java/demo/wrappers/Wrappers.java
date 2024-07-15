package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    private WebDriver driver;
    private WebDriverWait wait;
    String currntTime = "";

    public Wrappers(WebDriver driver, int timee) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timee));
    }

    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String date7DaysAgo() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String dateTime = sevenDaysAgo.format(dateFormatter);
        String[] arr = dateTime.trim().split("\\s+");
        return arr[0];
    }

    public String currentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        currntTime = formattedTime;
        String[] arrT = formattedTime.trim().split("\\s+");
        char[] arrC = arrT[0].toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=4; i++) {
            sb.append(arrC[i]);
        }
        return sb.toString();
    }

    public String amORpm() {
        char[] chArr = currntTime.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=chArr.length-2; i<chArr.length; i++) {
            sb.append(chArr[i]);
        }
        return sb.toString();
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
