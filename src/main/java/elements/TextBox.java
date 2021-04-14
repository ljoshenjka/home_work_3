package elements;

import elements.base.BaseField;
import elements.base.EditableField;
import org.openqa.selenium.By;

/**
 * Represents editable text field
 */
public class TextBox extends EditableField {

    static final String KEYWORD_EMPTY = "$EMPTY";

    public TextBox(BaseField parent, By locator) {
        super(parent, locator);
    }

    public TextBox(By locator) {
        super(locator);
    }

    public TextBox(By iosBy, By androidBy) {
        super(iosBy, androidBy);
    }

    public TextBox(BaseField parent, By iosBy, By androidBy) {
        super(parent, iosBy, androidBy);
    }

    public void setValueOrEmpty(String value) {
        if (value.equals(KEYWORD_EMPTY)) {
            getWebElement().clear();
        } else {
            setValue(value);
        }
    }
}
