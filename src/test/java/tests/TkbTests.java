package tests;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


import static helpers.EnvTkb.*;
import static io.qameta.allure.Allure.step;


@Epic("QA.GURU QA automation course")
@Story("Selenide TKB tests")
@Tag("tkb_tests")
class TkbTests extends TestBase {
    @Test
    @Story("TKB deposits button press test by closest A to the text")
    @Description("Open page, find Вклады button, select closest A, then click")
    void pageOpenButtonClickClosestA() {
            step ("Open Tinkoff main page", () -> {
            open(url);
            });
            step("Locate and press Вклады on page top", () -> {
                $(byText("Вклады")).closest("a").click();
                $("h1").shouldHave(text("Откройте вклад"));
            });
            }
    @Test
    @Story("TKB deposits button press test")
    @Description("Open page, find Вклады button by href=\'/deposit/\', then click")
    void pageOpenButtonClickbyHref() {
        step ("Open Tinkoff main page", () -> {
            open(url);
        });
        step("Locate and press Вклады by href ", () -> {
            $("[href=\'/deposit/\']").click();
            $("h1").shouldHave(text("Откройте вклад"));
        });
    }


}//class



            //            step("Click Edit Profile", ()->{
//                $(byPartialLinkText("Edit Profile")).click();
//            });
//
//            step("Edit about info, Life Events link must be available on the page", ()->{
//                $(byText("Edit your about info")).click();
//            });
//            step("Go to Life Events and check if 'Add a life event' exists", ()->{
//                $(byLinkText("Life Events")).click();
//            });
//            step("Click on Add a life event", ()->{
//                $(byText("Add a life event")).click();
//            });
//            step("Click on Relationship, then click on New Relationship, then click on SaySomething, then set stupid string, then click share", ()->{
//
//                $(byText("Relationship")).click();
//                $(byText("New Relationship")).click();
//                $(withText("Say something")).click();
//                $(getFocusedElement()).sendKeys("112233445566");
//                $(byText("Share")).click();
//
//            });
//            step("homepage and check we have Relationship update in the timeline", ()->{
//
//                $(byLinkText("Home")).click(); // works
//                $("body").shouldHave(text("112233445566"));
////                $(byText("Timeline")).shouldBe(visible);
////                $(byText("Timeline")).click();
////                $(byTagName("div")).$(byAttribute("role", "feed")).shouldHave(text("112233445566"));
//            });
//
