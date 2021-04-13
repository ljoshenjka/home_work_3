package utils;

import constants.GlobalParams;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;
import java.util.concurrent.Callable;

public class WaitUtil {

    public static ConditionFactory await(String msg, int timeout) {
        return Awaitility.await(msg)
                .atMost(Duration.ofSeconds(timeout))
                .pollInSameThread()
                .pollInterval(Duration.ofMillis(500))
                .pollDelay(Duration.ZERO)
                .ignoreExceptionsInstanceOf(WebDriverException.class);
    }

    public static void waitUntil(Callable<Boolean> condition) {
        await(null, GlobalParams.WAIT_APP).until(condition);
    }

    public static void waitUntil(Callable<Boolean> condition, String msg) {
        await(msg, GlobalParams.WAIT_APP).until(condition);
    }

    public static void waitUntilWithTimeout(Callable<Boolean> condition, int timeout) {
        await(null, timeout).until(condition);
    }

    public static void waitUntilWithTimeout(Callable<Boolean> condition, String msg, int timeout) {
        await(msg, timeout).until(condition);
    }
}
