package pages;

import elements.Button;
import io.appium.java_client.MobileBy;
import utils.WebDriverUtil;

import java.util.List;

public class LeftMenuPage {

    public static Button btnAccountSpinner = new Button(MobileBy.id("account_spinner"));

    public static List<String> getAccountList() {
        return WebDriverUtil.getStringListFromWebElementsList(WebDriverUtil.getElements(MobileBy.id("title")));
    }

    public static void selectAccountByName(String accountName) {
        Button btnAccount = new Button(MobileBy.xpath(String.format("//*[@text='%s']", accountName)));
        btnAccount.click();
    }
}
