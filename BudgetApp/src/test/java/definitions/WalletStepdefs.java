package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import menu.MenuManageWallets;
import model.Wallet;
import model.WalletList;
import service.WalletService;

import java.util.Scanner;

import static org.junit.Assert.*;


public class WalletStepdefs {
    Wallet wallet;

    WalletList walletList;
    WalletService walletService;

    @Given("There are no wallets at wallet list")
    public void thereAreNoWalletsAtWalletList() { walletList = new WalletList(); }

    @Given("There is one wallet named {string} on wallet list")
    public void thereIsOneWalletNamedOnWalletList(String walletName) {
        walletList = new WalletList();
        walletList.addWallet(walletName, "client");
    }

    @Given("There is {string} wallet named {string}")
    public void thereIsWalletNamed(String ownerEmail, String walletName) {
        walletService = new WalletService(new Scanner(System.in));
        walletService.addWallet(walletName,ownerEmail);
        walletService.setLoggedInUser(ownerEmail);
        walletService.setCurrentWalletName(walletName);
//        wallet = new Wallet(walletName,ownerEmail);
    }

    @Given("There is {string} wallet named {string} and {string} shared user")
    public void thereIsWalletNamedAndSharedUser(String ownerEmail, String walletName, String sharedUser) {
        thereIsWalletNamed(ownerEmail,walletName);
        walletService.getWalletByName(walletName).addSharedUserEmail(sharedUser);
    }

    @When("I add new wallet named {string}")
    public void iAddNewWalletNamed(String walletName) { walletList.addWallet(walletName, "client"); }

    @When("I archive wallet named {string}")
    public void iArchiveWalletNamed(String walletName) { walletList.archiveWallet(walletName); }

    @When("I {string} add new wallet named {string}")
    public void iNameAddNewWalletNamed(String ownerEmail, String walletName) {
        walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser(ownerEmail);
        walletService.setCurrentWalletName(walletName);
        walletService.addWallet(walletName,ownerEmail);
    }

    @When("I {string} add new wallet named {string} and {string}")
    public void iAddNewWalletNamedAnd(String ownerEmail, String wallet1Name, String wallet2Name) {
        iNameAddNewWalletNamed(ownerEmail,wallet1Name);
        walletService.addWallet(wallet2Name,ownerEmail);
    }

    @When("I add user {string} to wallet named {string}")
    public void iAddUserToWalletNamed(String anotherUserName, String walletName) {
        if (!walletService.hasUserAccess(anotherUserName))
            walletService.getWalletByName(walletName).addSharedUserEmail(anotherUserName);
//        wallet.addSharedUserEmail(anotherUserName);
    }

    @Then("Wallet list contains wallet named {string}")
    public void walletListContainsWalletNamed(String walletName) {
        assertTrue(walletList.getOrderedWalletsString().contains(walletName));
    }

    @Then("Wallet list is empty")
    public void walletListIsEmpty() { assertEquals(0, walletList.getLength()); }

    @Then("I {string} can check my wallets")
    public void iCanCheckMyWallets(String ownerEmail) {
        MenuManageWallets menuManageWallets = new MenuManageWallets(walletService);
        assertEquals(new StringBuilder("Available wallets for user ")
                        .append(ownerEmail).append(": ")
                        .append(walletService.getUserWalletsNames(ownerEmail)).toString(),
                menuManageWallets.getUserWalletsString());
    }

    @Then("{string} has access to wallet named {string}")
    public void hasAccessToWalletNamed(String anotherUserName, String walletName) {
        assertTrue(walletService.getWalletByName(walletName).getSharedUsersEmails().contains(anotherUserName));
    }

    @Then("{string} is not in shared users of wallet {string}")
    public void isNotInSharedUsersOfWallet(String userEmail, String walletName) {
        assertFalse(walletService.getWalletByName(walletName).getSharedUsersEmails().contains(userEmail));

    }

    @Then("{string} is not repeated in shared users of wallet {string}")
    public void isNotRepeatedInSharedUsersOfWallet(String userEmail, String walletName) {
        assertTrue(walletService.getWalletByName(walletName).getSharedUsersEmails().contains(userEmail));
        assertEquals(walletService.getWalletByName(walletName).getSharedUsersEmails().size(),1);
    }
}
