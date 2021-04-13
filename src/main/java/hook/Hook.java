package hook;

import constants.GlobalParams;
import helpers.LogHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriverException;
import utils.WebDriverSetup;
import utils.WebDriverUtil;

import java.net.MalformedURLException;

public class Hook {

    @Before
    public void initialize(Scenario scenario) throws MalformedURLException {
        if (WebDriverUtil.getDriver() != null) {
            // reuse previously created web driver instance if present
            try {
                WebDriverUtil.launchApp();
            } catch (WebDriverException wde) {
                LogHelper.getLogger().error("Some error in existing driver: ", wde);
                WebDriverUtil.closeDriverSession();
            }
        } else {
            // create new web driver instance
            LogHelper.getLogger().info("Start device: " + GlobalParams.SELENIUM_DRIVER);
            WebDriverUtil.setDriver(WebDriverSetup.setupWebDriver(GlobalParams.SELENIUM_DRIVER));

            WebDriverUtil.setImplicitWait();
        }
        LogHelper.getLogger().info("Scenario '" + scenario.getName() + "' STARTED");
    }

    @After
    public void tearDown(Scenario scenario) {
        LogHelper.getLogger().info("Scenario '" + scenario.getName() + "' " + scenario.getStatus().toString());
        WebDriverUtil.closeDriverSession();
    }
}
