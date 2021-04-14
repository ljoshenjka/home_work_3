package pages;

import elements.Button;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import utils.WebDriverUtil;

import java.util.List;

public class RightMenuPage {

    public static Button btnCategories = new Button(MobileBy.id("categories_panel"));
    public static Button btnAccounts = new Button(MobileBy.id("accounts_panel"));
    public static Button btnCurrencies = new Button(MobileBy.id("currency_panel"));
    public static Button btnSettings = new Button(MobileBy.id("settings_panel"));

    public static List<String> getAccountList() {
        List<WebElement> accountElementList = WebDriverUtil.getElements(MobileBy.xpath("//*[ends-with(@resource-id, 'relativeLayoutManageCategoriesListItem')]//*[contains(@resource-id, 'textViewName')]"));
        return WebDriverUtil.getStringListFromWebElementsList(accountElementList);
    }
}
