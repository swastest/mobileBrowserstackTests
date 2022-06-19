package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import owner.LocalMobileInterface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

// тут обязательно наследуемся
public class LocalMobileDriver implements WebDriverProvider {
    static LocalMobileInterface config = ConfigFactory.create(LocalMobileInterface.class,
            System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            //аппиум порт локальный
            return new URL(config.localURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    //аннтоация
@Override
    public WebDriver createDriver(Capabilities capabilities) {
    // метод для этоно описан ниже
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(config.platformName());
        options.setDeviceName(config.deviceName());
        options.setPlatformVersion(config.osVersion());
        options.setApp(app.getAbsolutePath());
    // пакет, где лежит приложение, путь в телефоне
        options.setAppPackage("org.wikipedia.alpha");
        // команда запуска - высечено на сердже мобильного разработчика, можно его даже разбудить и он это скажет
        options.setAppActivity("org.wikipedia.main.MainActivity");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    // метод для получения App
    private File getApp() {
        // ссылка, где можно скачать Апк
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/" +
                "releases/download/latest/app-alpha-universal-release.apk";
        // ссылка, куда положить (или где хранится АПК)
        String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";


        File app = new File(appPath);
        if (!app.exists()) {   // если App не найден, то мы его скачаем
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);  //  и сохраним по пути appPath
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }
}
