package tests.browserstack_samples;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
@Tag("android")
public class HomeWorkTest2 extends TestBase {
    @DisplayName("======Проверка заголовка статьи, что он соответствует запросу поиска")
    @Test
    void homeWorkWikiTest() {
        back();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Russia");
        $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
        $(AppiumBy.xpath("//android.widget.TextView[@text='Russia']"))
                .shouldHave(Condition.text("Russia"));

    }
}
