package pages;

import com.google.common.collect.ImmutableMap;
import elements.Button;
import elements.Label;
import io.appium.java_client.MobileBy;

import java.util.Map;

public class NewBalancePage {

    public static Button btnClear = new Button(MobileBy.id("buttonKeyboardClear"));
    public static Button btnChooseCategory = new Button(MobileBy.id("keyboard_action_button"));
    public static Label lblValue = new Label(MobileBy.id("amount_text"));

    private static final Map<Character, Button> NUMERIC_KEYBOARD = ImmutableMap.<Character, Button>builder()
            .put('0', new Button(MobileBy.id("buttonKeyboard0")))
            .put('1', new Button(MobileBy.id("buttonKeyboard1")))
            .put('2', new Button(MobileBy.id("buttonKeyboard2")))
            .put('3', new Button(MobileBy.id("buttonKeyboard3")))
            .put('4', new Button(MobileBy.id("buttonKeyboard4")))
            .put('5', new Button(MobileBy.id("buttonKeyboard5")))
            .put('6', new Button(MobileBy.id("buttonKeyboard6")))
            .put('7', new Button(MobileBy.id("buttonKeyboard7")))
            .put('8', new Button(MobileBy.id("buttonKeyboard8")))
            .put('9', new Button(MobileBy.id("buttonKeyboard9")))
            .put('.', new Button(MobileBy.id("buttonKeyboardDot")))
            .put('=', new Button(MobileBy.id("buttonKeyboardEquals")))
            .put('+', new Button(MobileBy.id("buttonKeyboardPlus")))
            .put('-', new Button(MobileBy.id("buttonKeyboardMinus")))
            .put('*', new Button(MobileBy.id("buttonKeyboardMultiply")))
            .put('/', new Button(MobileBy.id("buttonKeyboardDivide")))
            .build();

    public static void enterNumericValue(String value) {
        for (Character digit : value.toCharArray()) {
            NUMERIC_KEYBOARD.get(digit).click();
        }
    }

    public static void selectBalanceCategoryByName(String name) {
        Button btnCategory = new Button(MobileBy.xpath(String.format("//*[@text='%s']", name)));
        btnCategory.click();
    }
}
