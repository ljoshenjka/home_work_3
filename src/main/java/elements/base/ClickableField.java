package elements.base;

import org.openqa.selenium.By;
import utils.WebDriverUtil;

abstract public class ClickableField extends BaseField {
    public ClickableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public ClickableField(By locator) {
        super(locator);
    }

    public ClickableField(By iosBy, By androidBy) {
        super(iosBy, androidBy);
    }

    public ClickableField(BaseField parent, By iosBy, By androidBy) {
        super(parent, iosBy, androidBy);
    }

    public void click() {
        WebDriverUtil.click(getWebElement());
    }
}
