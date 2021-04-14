package steps;

import base.BaseStep;
import helpers.LogHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.App;
import utils.WebDriverUtil;

public class CommonSteps extends BaseStep {

    @Given("user opens application")
    public void userOpensApp() {
        WebDriverUtil.launchApp();
        LogHelper.getLogger().info("APP OPENED");
    }

    @Then("checks that navigation bar title is {string}")
    public void checksThatNavigationBarTitleIs(String title) {
        Assert.assertEquals("Wrong title in navigation bar", title, App.navigationBar.lblScreenName.getValue());

    }

    @And("checks that {string} is selected as account")
    public void checksThatIsSelectedAsAccount(String accountName) {
        Assert.assertEquals("Wrong account name in navigation bar", accountName, App.navigationBar.lblAccountName.getValue());
    }

    @When("user opens left navigation menu")
    public void userOpensLeftNavigationMenu() {
        App.navigationBar.btnStrawberryMenu.click();
    }

    @When("user opens right navigation menu")
    public void userOpensRightNavigationMenu() {
        App.navigationBar.btnKebabMenu.click();
    }
}
