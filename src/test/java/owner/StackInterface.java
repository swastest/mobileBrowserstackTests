package owner;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:properties/bStackDriver.properties"
})

public interface StackInterface extends Config {
    String username();
    String access_key();
    String project();
    String build();
    String name();
    String app();
    String device();
    String osVersion();
    String bsUrl();

}
