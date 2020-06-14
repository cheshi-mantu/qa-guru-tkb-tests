package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.CustomWebDriver;
import helpers.FileReadHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.EnvTkb.selenoid_url;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
    @BeforeEach
    public void BeforeEachTest(){
        if (selenoid_url == null) {
            Configuration.remote = FileReadHelper.getStringFromFile("selenoid_url.secret")+":4444/wd/hub";
        } else {
            Configuration.remote = selenoid_url + ":4444/wd/hub";
        }
        Configuration.browser = CustomWebDriver.class.getName();
        Configuration.startMaximized = true;
    }
    @AfterEach
    public void closeBrowser(){
        closeWebDriver();
    }
}