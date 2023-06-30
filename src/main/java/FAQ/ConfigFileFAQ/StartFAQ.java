package FAQ.ConfigFileFAQ;

import FAQ.General_Questions;
import FAQ.Wallet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class StartFAQ {
    @Test(alwaysRun = true, description = "Общие вопросы")
    public void genQue (WebDriver driver){
        try {
            General_Questions faq = new General_Questions();
            faq.general_Questions(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Test(alwaysRun = true, description = "Кошелек")
    public void genwal (WebDriver driver){
        try {
            Wallet genfaq = new Wallet();
            genfaq.genWallet(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}
}
