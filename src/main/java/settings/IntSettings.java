package settings;

import java.io.IOException;
import java.util.Map;

public interface IntSettings {
    Map<String, String> getSettings() throws IOException;
}
