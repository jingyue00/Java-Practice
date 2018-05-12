package edu.nyu.cs9053.homework7;

public class CryptoWalletTransfer {

    public <T extends Cryptocurrency> void transfer(CryptoWallet<? extends T> from, CryptoWallet<? super T> into) {
        if (from == null || into == null) {
            throw new IllegalArgumentException("Input should not be null");
        }
        for (int i = 0; i < from.getSize(); i++) {
            into.add(from.get(i));
        }
    }
}