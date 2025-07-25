package settings;

import java.io.File;

public abstract class AbsSettings {

    protected File propertiesFile;

    public AbsSettings(String fileName) {
        String rootPath = System.getProperty("user.dir");
        propertiesFile = new File(rootPath + "/src/main/resources/" + fileName);
    }
}
