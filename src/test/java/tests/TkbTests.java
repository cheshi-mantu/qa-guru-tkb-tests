package tests;


import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import static helpers.EnvTkb.*;
import static io.qameta.allure.Allure.step;

@Epic("QA.GURU QA automation course")
@Story("Selenide TKB tests homeworx")

@Tag("tkb_tests")

class TkbTests extends TestBase {
    @BeforeEach
    void MaxBrowserWindow(){
        Configuration.startMaximized = true;
    }

    @Test
    @Description("Open page, find Вклады button, select closest A, then click")
    @DisplayName("Open main page, click on Вклады button by closest A")
    void pageOpenButtonClickClosestA() {
            step ("Open Tinkoff main page", () -> open(url));
            step("Locate and press Вклады on page top", () -> {
                $(byText("Вклады")).closest("a").click();
                $("h1").shouldHave(text("Откройте вклад"));
            });
            }
    @Test
    @Description("Open page, find Вклады button by href='/deposit/', then click")
    @DisplayName("Open main page, click on Вклады button by HREF")
    void pageOpenButtonClickbyHref() {
        step ("Open Tinkoff main page", () -> open(url));
        step("Locate and press Вклады by href ", () -> {
            $("[href='/deposit/']").click();
            $("h1").shouldHave(text("Откройте вклад"));
        });
    }
    @Test
    @Description("Open page, find Вклады button by qa type , then click")
    @DisplayName("Open main page, click on Вклады button by uikit index 2")
    void pageOpenButtonClickbyQaType() {
        step ("Open Tinkoff main page", () -> open(url));
        step("Locate and press Вклады by qa type", () -> {
            $$("[data-qa-type='uikit/tabsWithDroplist.item']").get(2).click();
            $("h1").shouldHave(text("Откройте вклад"));
        });
    }
    @Test
    @Description("Open page, find Вклады button by div with data-tabs-with-droplist-index , then click")
    @DisplayName("Open main page, click on Вклады button by DIV")
    void pageOpenButtonClickbyDiv() {
        step ("Open Tinkoff main page", () -> open(url));
        step("Locate and press Вклады by qa type", () -> {
            $("[data-tabs-with-droplist-index='2']").click();
            $("h1").shouldHave(text("Откройте вклад"));
        });
    }
    @Test
    @Description("Open page, find Вклады button by div with data-tabs-with-droplist-index , then click")
    @DisplayName("Open main page, click on Вклады button by data-index")
    void pageOpenButtonClickbyHiLevelDiv() {
        step ("Open Tinkoff main page", () -> open(url));
        step("Locate and press Вклады by qa type", () -> {
            $("[data-index='2']").click();
            $("h1").shouldHave(text("Откройте вклад"));
        });
    }
    @Test
    @Description("Open page, find Вклады button, click, set value 2500000 to deposit amount, press tab, check value is stored")
    @DisplayName("Open main page, click on Вклады button, set value for the amount, check if set")
    void pageDepositWidgetAmount() {
        step ("Open Tinkoff main page", () -> open(url));
        step("Locate and press Вклады by qa type", () -> {
            $("[href='/deposit/']").click();
            $("[data-qa-data='uikit/pageHeader']").shouldHave(text("Вклады"));
            $("[data-qa-type='uikit/inputBox.inputContainer'] input").setValue("2500000");
//            $("[data-qa-type='uikit/inputBox.inputContainer'] input").pressTab();
            //Selenide.actions().sendKeys(Keys.TAB).perfrom();
            $("[data-qa-type='uikit/inputBox.inputContainer'] input").shouldHave(value("2500000"));
        });
    }
    @Test
    @Description("Open page, find Вклады button by div with data-tabs-with-droplist-index , then click")
    @DisplayName("Open DEPOSITES page, click DDownl list, select USD, check")
    void pageDepositWidgetDropDown () {
        step ("Open Tinkoff Ddeposits page", () -> open(url+"/deposit/"));
        step("Locate currency selection drop-down list with Рубли by default", () -> {
            $("[data-qa-type='uikit/select.value']").shouldHave(text("Рубли"));
//            $("[role='listbox']").click(); // this works
        });
        step("Click drop-down item and select Доллары", () -> {
            $("[data-qa-type='uikit/inputBox']", 2).click(); // this works
//            $("[data-qa-type='uikit/dropdown.item']").$(byText("Доллары США")).click();
            $("[data-qa-type='uikit/scroll']").$(byText("Доллары США")).click();
//            $("[role='heading']").$(byText("Доллар")).click();
            $("[data-qa-type='uikit/inputBox']",2 ).$("[data-qa-type='uikit/select.value']").shouldHave(text("Доллары США"));
//          setTimeout(function(){debugger},5000) <<< = in console of DEv Tools
        });


    }

    @Test
    @Description("Open deposits page, select USD, hover the pie and check text contains $")
    @DisplayName("Open DEPOSITES page, select USD from DDown, hover mouse over Pie, check all have $ sign")
    void pageDepositWidgetPie () {
        step ("Open Tinkoff Deposits page", () -> open(url+"/deposit/"));
        step("Locate currency selection drop-down list with Рубли by default", () -> {
            $("[data-qa-type='uikit/select.value']").shouldHave(text("Рубли"));
//            $("[role='listbox']").click(); // this works
        });
        step("Click drop-down item and select Доллары", () -> {
            $("[data-qa-type='uikit/inputBox']", 2).click(); // this works
            $("[data-qa-type='uikit/scroll']").$(byText("Доллары США")).click();
            $("[data-qa-type='uikit/inputBox']",2 ).$("[data-qa-type='uikit/select.value']").shouldHave(text("Доллары США"));
        });
        step("Hover Pie, Pie should have $ sign in both states: without hover and with hover", () -> {
            $("span[data-qa-file='Money']").shouldHave(text("$"));
            $("div[data-qa-file='Pie']").hover();
            $("span[data-qa-file='Money']").shouldHave(text("$"));
        });
    }

    @Test
    @Description("Open deposits page, uncheck 1st checkbox")
    @DisplayName("Open DEPOSITES page, check if CheckBoxes are visible, click, check it is unchecked")
    void pageDepositCheckBox () {
        step ("Open Tinkoff Deposits page", () -> open(url+"/deposit/"));
        step("Check check boxes are visible", () -> {
            $("[data-qa-file='DepositCalculator']").shouldHave(text("Повысить ставку по вкладу"));
        });
        step("Uncheck upper checkbox, validate if the input type checkbox is unchecked", () -> {
            $("[data-qa-type='uikit/checkbox'] input",0).shouldBe(checked);
            $("[data-qa-file='DepositCalculator']").$(byText("Повысить ставку по вкладу")).click();
            $("[data-qa-type='uikit/checkbox'] input",0).shouldNotBe(checked);
        });
    }

    @Test
    @Description("Open main page, click Бизнес then Регистрация бизнеса then check Регистрация ИП из презент")
    @DisplayName("Open DEPOSITES page, check if CheckBoxes are visible, click, check it is unchecked")
    void pageIpRegistration () {
        step ("Open Tinkoff main page", () -> open(url));
        step("Click big БИЗНЕС in the header", () -> {
            $("[href='/business/']").click();
            $("html").shouldHave(text("Регистрация бизнеса"));
        });
        step("Click on Регистрация бизнеса and chek if there is Регистрация ИП section", () -> {
            $("[data-tabs-with-droplist-index='1']").click();
            $("html").shouldHave(text("Регистрация ИП"));
        });
    }


}//class

