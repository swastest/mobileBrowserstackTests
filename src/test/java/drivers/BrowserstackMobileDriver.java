package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import owner.StackInterface;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    static StackInterface conf = ConfigFactory.create(StackInterface.class,
            System.getProperties());
    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", conf.username());
        mutableCapabilities.setCapability("browserstack.key", conf.access_key());

        // Set URL of the application under test

        mutableCapabilities.setCapability("app", conf.app());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", conf.device());
        mutableCapabilities.setCapability("os_version", conf.osVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", conf.project());
        mutableCapabilities.setCapability("build", conf.build());
        mutableCapabilities.setCapability("name", conf.name());
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {

        try {
            return new URL(conf.bsUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
