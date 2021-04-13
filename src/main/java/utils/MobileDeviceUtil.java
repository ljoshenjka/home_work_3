package utils;

import constants.PropertyConfigs;
import helpers.MobileDevice;

public class MobileDeviceUtil {

    public static MobileDevice getMobileDeviceByName(String deviceName) {
        String yamlName = WebDriverUtil.isIOS ? "IOSMobileDevices" : "AndroidMobileDevices";
        return (MobileDevice) DataProvider.getData("devices/" + yamlName, deviceName);
    }

    public static MobileDevice getMobileDevice() {
        String deviceName = ConfigReader.getInstance().getValue(PropertyConfigs.MOBILE_DEVICE_NAME);
        return getMobileDeviceByName(deviceName);
    }
}
