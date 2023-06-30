package FAQ.ConfigFileFAQ;

import FAQ.General_Questions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class StartFAQ {
    @Test(alwaysRun = true, description = "")
    public void genQue (WebDriver driver){
        try {
            General_Questions faq = new General_Questions();
            faq.general_Questions(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    /*@Test(alwaysRun = true, description = "")
    public void (WebDriver driver){
        try {

        }catch (Exception e) {throw new RuntimeException(e);}}*/
}
