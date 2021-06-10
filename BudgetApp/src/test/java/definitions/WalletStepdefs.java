package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import model.WalletList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WalletStepdefs {

    WalletList walletList;

    @Given("There are no wallets at wallet list")
    public void thereAreNoWalletsAtWalletList() { walletList = new WalletList(); }

    @Given("There is one wallet named {string} on wallet list")
    public void thereIsOneWalletNamedOnWalletList(String walletName) {
        walletList = new WalletList();
        walletList.addWallet(walletName, "client");
    }

    @When("I add new wallet named {string}")
    public void iAddNewWalletNamed(String walletName) { walletList.addWallet(walletName, "client"); }

    @When("I archive wallet named {string}")
    public void iArchiveWalletNamed(String walletName) { walletList.archiveWallet(walletName); }

    @Then("Wallet list contains wallet named {string}")
    public void walletListContainsWalletNamed(String walletName) {
        assertTrue(walletList.getOrderedWalletsString().contains(walletName));
    }

    @Then("Wallet list is empty")
    public void walletListIsEmpty() { assertEquals(0, walletList.getLength()); }

}
