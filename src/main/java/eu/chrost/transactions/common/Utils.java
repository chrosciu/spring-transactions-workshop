package eu.chrost.transactions.common;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class Utils {
    public static void runCatching(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }
}
