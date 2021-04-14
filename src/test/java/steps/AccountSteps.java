package steps;

import base.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LeftMenuPage;
import pages.RightMenuPage;
import utils.WaitUtil;

import java.util.List;

public class AccountSteps extends BaseStep {

    @And("user clicks account spinner in left navigation menu")
    public void userClicksAccountSpinnerInLeftNavigationMenu() {
        LeftMenuPage.btnAccountSpinner.click();
    }

    @Then("account types shown in spinner")
    public void accountTypesShownInSpinner(List<String> accounts) {
        WaitUtil.waitUntil(() -> LeftMenuPage.getAccountList().size() == accounts.size());
        Assert.assertEquals("Wrong account list", accounts, LeftMenuPage.getAccountList());
    }

    @And("user select Accounts from right navigation menu")
    public void userSelectAccountsFromRightNavigationMenu() {
        RightMenuPage.btnAccounts.click();
    }

    @Then("account types shown in accounts menu")
    public void accountTypesShownInAccountsMenu(List<String> accounts) {
        Assert.assertEquals("Wrong account list", accounts, RightMenuPage.getAccountList());
    }
}
