package tools;

import java.util.UUID;

public class RandomStringUUID {
    public String generatorId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
