package elements;

import elements.base.BaseField;
import elements.base.StaticField;
import org.openqa.selenium.By;

public class Label extends StaticField {
    public Label(BaseField parent, By locator) {
        super(parent, locator);
    }

    public Label(By locator) {
        super(locator);
    }

    public Label(By iosBy, By androidBy) {
        super(iosBy, androidBy);
    }

    public Label(BaseField parent, By iosBy, By androidBy) {
        super(parent, iosBy, androidBy);
    }
}
