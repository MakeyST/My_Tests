package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
        private WebDriver driver;
        private WebDriverWait wait;

        public WaitUtils(WebDriver driver, Duration timeout) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, timeout);
        }

        public WebElement waitForElementToBeVisible(By locator) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public WebElement waitForElementToBeClickable(By locator) {
                return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

        public void waitForFrameToBeAvailableAndSwitchToIt(By frameLocator) {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        }

        public void waitForTextToBePresentInElement(By locator, String text) {
                wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        }

        public void waitForPageToLoad() {
                wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }
}
