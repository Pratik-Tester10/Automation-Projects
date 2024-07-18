package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    
    private WebDriver driver;
    private WebDriverWait wait;

    public Wrappers(WebDriver driver, int timee) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timee));
    }

    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.getText();
    }

    public void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void hitEnter(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(Keys.ENTER);
    }

    public void arrowDown(By locator, int n) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        for(int i=1; i<=n; i++) {
            element.sendKeys(Keys.ARROW_DOWN);
        }
    }

    public void arrowUp(By locator, int n) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        for(int i=1; i<=n; i++) {
            element.sendKeys(Keys.ARROW_UP);
        }
    }

    public void arrowLeft(By locator, int n) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        for(int i=1; i<=n; i++) {
            element.sendKeys(Keys.ARROW_LEFT);
        }
    }

    public void arrowRight(By locator, int n) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        for(int i=1; i<=n; i++) {
            element.sendKeys(Keys.ARROW_RIGHT);
        }
    }

}
