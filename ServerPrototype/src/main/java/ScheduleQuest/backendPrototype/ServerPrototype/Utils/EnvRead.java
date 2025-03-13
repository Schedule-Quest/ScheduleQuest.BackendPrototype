package ScheduleQuest.backendPrototype.ServerPrototype.Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRead {
    private static final Dotenv dotenv = Dotenv.load();

    public static String loadEnv(String key) {
        return dotenv.get(key);
    }
}
