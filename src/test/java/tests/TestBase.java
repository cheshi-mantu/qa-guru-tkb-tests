package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.FileReadHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.EnvTkb.selenide_remote;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
    @BeforeEach
    public void BeforeEachTest(){
//        Configuration.browser = "opera";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("EnableVNC", true);
        capabilities.setCapability("EnableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = selenide_remote;
        if (selenide_remote.equals("null")) {
            System.setProperty("selenide_remote", FileReadHelper.getStringFromFile("selenide_remote.secret")+":4444/wd/hub");
        }
    }
    @AfterEach
    public void closeBrowser(){
        closeWebDriver();
    }
}
