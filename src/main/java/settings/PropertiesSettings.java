package settings;

import settings.IntSettings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesSettings extends AbsSettings implements IntSettings {

    public PropertiesSettings(String fileName) {
        super(fileName);
    }

    @Override
    public Map<String, String> getSettings() throws IOException {
        Properties settingProperties = new Properties();

        settingProperties.load(new FileInputStream(propertiesFile));

        Map<String, String> settings = new HashMap<>();
        for(Map.Entry entry: settingProperties.entrySet()){
            settings.put((String) entry.getKey(), (String) entry.getValue());
        }

        return settings;
    }
}
