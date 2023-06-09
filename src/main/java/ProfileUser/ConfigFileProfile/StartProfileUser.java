package ProfileUser.ConfigFileProfile;

import ProfileUser.*;
import ProfileUser.СontrolProfile.ControlPromoCode;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
@Owner("Makeenkov Igor")
public class StartProfileUser {
    @Test(alwaysRun = true, description = "Проверка Никнейма англ и рус + сохранение + проверка")
    public void nicknameEngRus (WebDriver driver){
        try {
            SettingNickname sNik = new SettingNickname();
            sNik.setNickname(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Test(alwaysRun = true, description = "Проверка выбора языка англ и рус + сохранение + проверка")
    public void systemLanguage (WebDriver driver){
        try {
            SettingSystemLanguage sSysLang = new SettingSystemLanguage();
            sSysLang.settingSystemLanguage(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}


    @Test(alwaysRun = true, description = "Применение промокода")
    public void settingsPromoCode (WebDriver driver){
        try {
            SettingPromoCode ssettingPromoCode = new SettingPromoCode();
            ssettingPromoCode.settingPromoCode(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}


    @Test(alwaysRun = true, description = "Контроль промокода, что он активировался")
    public void controlPromocode (WebDriver driver){
        try {
            ControlPromoCode scontrolPromoCode = new ControlPromoCode();
            scontrolPromoCode.controlPromoCode(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Test(alwaysRun = true, description = "Смена пароля")
    public void settingsPassword (WebDriver driver){
        try {
            SettingPassword ssettingPasword = new SettingPassword();
            ssettingPasword.setPassword(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Test(alwaysRun = true, description = "Возврат пароля, на который был, а то авторизация упадет")
    public void settingsPasswordBack (WebDriver driver){
        try {
            SettingPasswordBack ssettingPaswordBack = new SettingPasswordBack();
            ssettingPaswordBack.setPasswordBack(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}




}