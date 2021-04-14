package steps;

import base.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.App;

import java.util.List;

public class MainPageSteps extends BaseStep {

    @Then("checks that spending categories are present on Main screen")
    public void checksThatSpendingCategoriesArePresentOnMainScreen(List<String> categories) {
        for (String category : categories) {
            Assert.assertTrue("Category not present: " + category, App.spendingCategories.get(category).isDisplayed());
        }
    }

    @And("expense button is present on Main screen")
    public void expenseButtonIsPresentOnMainScreen() {
        Assert.assertTrue("Expense button is not displayed", App.btnExpense.isDisplayed());
    }

    @And("income button is present on Main screen")
    public void incomeButtonIsPresentOnMainScreen() {
        Assert.assertTrue("Income button is not displayed", App.btnIncome.isDisplayed());
    }

    @And("user balance is {string}")
    public void userBalanceIs(String balance) {
        Assert.assertEquals("Wrong balance", "Balance " + balance, App.btnBalance.getValue());
    }

    @And("user income is {string}")
    public void userIncomeIs(String income) {
        Assert.assertEquals("Wrong income", income, App.lblIncomeAmount.getValue());
    }

    @And("user expense is {string}")
    public void userExpenseIs(String expense) {
        Assert.assertEquals("Wrong expense", expense, App.lblExpenseAmount.getValue());
    }

    @When("user opens {string} category from Main screen")
    public void userOpensCarCategoryFromMainScreen(String category) {
        App.spendingCategories.get(category).click();
    }

    @Then("checks that balance menu is expanded")
    public void checksThatBalanceMenuIsExpanded() {
        Assert.assertTrue("Balance filter button not shown", App.btnFilter.isDisplayed());
    }

    @When("user clicks balance on Main screen")
    public void userClicksBalanceOnMainScreen() {
        App.btnBalance.click();
    }

    @And("checks that strawberry menu is present in navigation bar")
    public void checksThatStrawberryMenuIsPresentInNavigationBar() {
        Assert.assertTrue("Strawberry menu is not displayed", App.navigationBar.btnStrawberryMenu.isDisplayed());
    }

    @And("checks that kebab menu is present in navigation bar")
    public void checksThatKebabMenuIsPresentInNavigationBar() {
        Assert.assertTrue("Kebab menu is not displayed", App.navigationBar.btnKebabMenu.isDisplayed());
    }

    @And("checks that search button is present in navigation bar")
    public void checksThatSearchButtonIsPresentInNavigationBar() {
        Assert.assertTrue("Search button is not displayed", App.navigationBar.btnSearch.isDisplayed());

    }

    @And("checks that transfer button is present in navigation bar")
    public void checksThatTransferButtonIsPresentInNavigationBar() {
        Assert.assertTrue("Transfer button is not displayed", App.navigationBar.btnTransfer.isDisplayed());

    }

    @When("user clicks income on Main screen")
    public void userClicksIncomeOnMainScreen() {
        App.btnIncome.click();
    }

    @When("user clicks expense on Main screen")
    public void userClicksExpenseOnMainScreen() {
        App.btnExpense.click();
    }
}
