package custom_elements;

import elements.Button;
import elements.Label;
import elements.base.BaseField;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class NavigationBar extends BaseField {
    public NavigationBar(BaseField parent, By locator) {
        super(parent, locator);
    }

    public NavigationBar(By locator) {
        super(locator);
    }

    public NavigationBar(By iosBy, By androidBy) {
        super(iosBy, androidBy);
    }

    public Button btnStrawberryMenu = new Button(MobileBy.AccessibilityId("Open navigation"));
    public Button btnKebabMenu = new Button(MobileBy.AccessibilityId("Settings"));
    public Button btnTransfer = new Button(MobileBy.AccessibilityId("Transfer"));
    public Button btnSearch = new Button(MobileBy.AccessibilityId("Search records"));
    public Button btnBack = new Button(MobileBy.AccessibilityId("Navigate up"));
    public Label lblAppName = new Label(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Monefy\")"));
    public Label lblScreenName = new Label(this, MobileBy.xpath(".//android.widget.TextView[1]"));
    public Label lblAccountName = new Label(this, MobileBy.xpath(".//android.widget.TextView[2]"));
}
