package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.App;
import pages.NewBalancePage;
import utils.WaitUtil;

public class NewBalanceSteps {

    @Then("checks that New Expense screen shown for category {string}")
    public void checksThatNewExpenseScreenShownForCategory(String category) {
        Assert.assertEquals("Wrong category screen", String.format("ADD '%s'", category.toUpperCase()), NewBalancePage.btnChooseCategory.getValue());
    }

    @Then("checks that New Income screen shown")
    public void checksThatNewIncomeScreenShown() {
        WaitUtil.waitUntil(() -> NewBalancePage.lblValue.isDisplayed(), "Income value not shown");
        Assert.assertEquals("Wrong category screen", "New income", App.navigationBar.lblScreenName.getValue());
    }

    @Then("checks that New Expense screen shown")
    public void checksThatNewExpenseScreenShown() {
        WaitUtil.waitUntil(() -> NewBalancePage.lblValue.isDisplayed(), "Expense value not shown");
        Assert.assertEquals("Wrong category screen", "New expense", App.navigationBar.lblScreenName.getValue());
    }

    @Then("user enters new balance {string}")
    public void userEntersNewBalance(String balance) {
        NewBalancePage.enterNumericValue(balance);
    }

    @Then("checks that balance is {string}")
    public void checksThatBalanceIs(String balance) {
        Assert.assertEquals("Wrong balance entered", balance, NewBalancePage.lblValue.getValue());
    }

    @Then("user clicks Choose Category")
    public void userClicksChooseCategory() {
        NewBalancePage.btnChooseCategory.click();
    }

    @Then("user selects {string} category from balance screen")
    public void userSelectsCategoryFromBalanceScreen(String category) {
        NewBalancePage.selectBalanceCategoryByName(category);
    }
}
