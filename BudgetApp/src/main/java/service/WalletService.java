package service;

import model.Entry;
import model.EntryList;
import model.User;
import model.Wallet;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import service.SerializerService;

public class WalletService {
    public List<Wallet> wallets;

    public WalletService()
    {

    }

    public void loadWalletsOnStart(SerializerService serializerService) throws FileNotFoundException {
        wallets = (List<Wallet>) serializerService.readObjectFromFile("Wallets");
        if (wallets == null) {
            wallets = new LinkedList<>();
        }
    }

    public List<Wallet> getUserWallets(String userEmail) {
       return wallets.stream()
               .filter(w -> w.getOwnerEmail().equals(userEmail) ||
                            w.getSharedUsersEmails().contains(userEmail))
               .collect(Collectors.toList()); // ALWAYS returns list, if nothing is found than empty.

    }

    public boolean checkIfWalletExists(String walletId) {
        return wallets.stream()
                .anyMatch(w -> w.getWalletId().equals(walletId));
    }

    public void addEntry(String walletId, Entry entry) {
        wallets.stream().filter(w -> w.getWalletId().equals(walletId)).findFirst()
                        .orElse(null) // optional? :(
                        .addEntry(entry);
    }

    public void addWallet(String walletId, String ownerEmail) {
        Wallet wallet = new Wallet(walletId, ownerEmail);
        wallets.add(wallet);
    }


//    public void setWallets(List<Wallet> wallets) {
//        this.wallets = wallets;
//    }
}
