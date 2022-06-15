package tests;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;

public class HomeWorkTests extends TestBase {
    @DisplayName("Проверка заголовка статьи, что он соответствует запросу поиска")
    @Test
    void homeWorkWikiTest() {
        back();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Russia");
        $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
        $(AppiumBy.xpath("//android.widget.TextView[@text='Russia']"))
                .shouldHave(Condition.text("Russia"));

    }

    @Test
    void homeWorkWikiCaptchaTest() {
        back();
        $(AppiumBy.xpath("//android.widget.TextView[@text='Edits']")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/positiveButton")).click();
        $(AppiumBy.xpath("//android.widget.TextView[@text='Create an account']"))
                .shouldHave(Condition.text("Create an account"));
        $(AppiumBy.xpath("//android.widget.EditText[@text='Username']")).click();
        $(AppiumBy.xpath("//android.widget.EditText[@text='Username']"))
                .sendKeys("QaGuruHello");

        $(AppiumBy.xpath("//android.widget.EditText[@text='Password']")).click();
        $(AppiumBy.xpath("//android.widget.EditText[@text='Password']"))
                .sendKeys("12345678");

        $(AppiumBy.xpath("//android.widget.EditText[@text='Repeat password']")).click();
        $(AppiumBy.xpath("//android.widget.EditText[@text='Repeat password']"))
                .sendKeys("12345678");

        $(AppiumBy.xpath("//android.widget.EditText[@text='Email (Optional)']")).click();
        $(AppiumBy.xpath("//android.widget.EditText[@text='Email (Optional)']"))
                .sendKeys("qaGurur123@google.com");

        $(AppiumBy.xpath("//android.widget.Button[@text='NEXT']")).click();

        $(AppiumBy.id("org.wikipedia.alpha:id/request_account_text"))
                .shouldHave(Condition.text("Can't see the image? Request an account"));
        $(AppiumBy.id("org.wikipedia.alpha:id/captcha_image")).shouldBe(Condition.visible);
        $(AppiumBy.id("org.wikipedia.alpha:id/captcha_submit_button")).shouldBe(Condition.visible);

    }
}