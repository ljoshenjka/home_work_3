package pages;

import com.google.common.collect.ImmutableMap;
import custom_elements.NavigationBar;
import elements.Button;
import elements.Label;
import elements.TextBox;
import io.appium.java_client.MobileBy;

import java.util.Map;

public class App {

    public static NavigationBar navigationBar = new NavigationBar(MobileBy.id("toolbar"));

    // Spending categories
    private static final String xptCategoryIcon = "//android.widget.FrameLayout[@resource-id='com.monefy.app.lite:id/piegraph']/android.widget.ImageView";

    public static Button btnCategoryFood = new Button(MobileBy.xpath(xptCategoryIcon + "[1]"));
    public static Button btnCategoryCar = new Button(MobileBy.xpath(xptCategoryIcon + "[2]"));
    public static Button btnCategoryTransport = new Button(MobileBy.xpath(xptCategoryIcon + "[3]"));
    public static Button btnCategoryEntertainment = new Button(MobileBy.xpath(xptCategoryIcon + "[4]"));
    public static Button btnCategoryHouse = new Button(MobileBy.xpath(xptCategoryIcon + "[5]"));
    public static Button btnCategoryTaxi = new Button(MobileBy.xpath(xptCategoryIcon + "[6]"));
    public static Button btnCategoryEatingOut = new Button(MobileBy.xpath(xptCategoryIcon + "[7]"));
    public static Button btnCategoryClothes = new Button(MobileBy.xpath(xptCategoryIcon + "[8]"));
    public static Button btnCategoryToiletry = new Button(MobileBy.xpath(xptCategoryIcon + "[9]"));
    public static Button btnCategorySports = new Button(MobileBy.xpath(xptCategoryIcon + "[10]"));
    public static Button btnCategoryHealth = new Button(MobileBy.xpath(xptCategoryIcon + "[11]"));
    public static Button btnCategoryCommunications = new Button(MobileBy.xpath(xptCategoryIcon + "[12]"));

    public static final Map<String, Button> spendingCategories = ImmutableMap.<String, Button>builder()
            .put("Food", btnCategoryFood)
            .put("Car", btnCategoryCar)
            .put("Transport", btnCategoryTransport)
            .put("Entertainment", btnCategoryEntertainment)
            .put("House", btnCategoryHouse)
            .put("Taxi", btnCategoryTaxi)
            .put("EatingOut", btnCategoryEatingOut)
            .put("Clothes", btnCategoryClothes)
            .put("Toiletry", btnCategoryToiletry)
            .put("Sports", btnCategorySports)
            .put("Health", btnCategoryHealth)
            .put("Communications", btnCategoryCommunications)
            .build();

    // Pie chart
    public static Label lblIncomeAmount = new Label(MobileBy.id("income_amount_text"));
    public static Label lblExpenseAmount = new Label(MobileBy.id("expense_amount_text"));

    // Balance menu
    public static Button btnBalance = new Button(MobileBy.id("balance_amount"));
    public static Button btnIncome = new Button(MobileBy.id("income_button"));
    public static Button btnExpense = new Button(MobileBy.id("expense_button"));

    // Expanded balance menu
    public static Button btnFilter = new Button(MobileBy.id("buttonChooseListSortingMode"));
    public static TextBox txbEmptyRecords = new TextBox(MobileBy.id("empty_message_text_view"));
}
