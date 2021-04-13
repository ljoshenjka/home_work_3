package constants;

import utils.ConfigReader;

public class GlobalParams {

    //Execution modes
    public static final boolean IS_FROM_MAVEN = Boolean.parseBoolean(ConfigReader.getInstance().getValue(PropertyConfigs.FROM_MAVEN));

    //Selenium parameters
    public static final String SELENIUM_DRIVER = ConfigReader.getInstance().getValue(PropertyConfigs.SELENIUM_DRIVER);

    //Wait constants
    public static final int WAIT_SELENIUM_IMPLICIT = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.SELENIUM_IMPLICITLY_WAIT));
    public static final int WAIT_APP = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.APP_WAIT));

    //Application parameters
    public static final String APP_PACKAGE = ConfigReader.getInstance().getValue(PropertyConfigs.APP_PACKAGE);
    public static final String APP_ACTIVITY = ConfigReader.getInstance().getValue(PropertyConfigs.APP_ACTIVITY);
}
