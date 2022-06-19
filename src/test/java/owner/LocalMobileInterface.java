package owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:resources/properties/local.properties")
public interface LocalMobileInterface extends Config {
    @DefaultValue("http://localhost:4723/wd/hub")
    String localURL();

    @DefaultValue("Pixel 4 API 30")
    String deviceName();

//    @DefaultValue("11.0")
    String osVersion();

    @DefaultValue("Android")
    String platformName();


}
