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
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("EnableVNC", true);
        capabilities.setCapability("EnableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        if (selenide_remote.equals("null")) {
            Configuration.remote = FileReadHelper.getStringFromFile("selenide_remote.secret")+":4444/wd/hub";
        } else {
            Configuration.remote = selenide_remote + ":4444/wd/hub";
        }
        System.setProperty("chromeoptions.args", "--disable-notifications");
        System.setProperty("chromeoptions.args", "intl.accept_languages=ru");
    }
    @AfterEach
    public void closeBrowser(){
        closeWebDriver();
    }
}
