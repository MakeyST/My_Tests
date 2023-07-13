package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class WaitForPageToLoad{
public static void waitForPageToLoad(WebDriver driver) throws IOException, InterruptedException{
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(webDriver->((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
        }
}
