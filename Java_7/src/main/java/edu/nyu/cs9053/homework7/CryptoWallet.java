package edu.nyu.cs9053.homework7;

public class CryptoWallet<T extends Cryptocurrency> extends Wallet<T> {

    public CryptoWallet(ArrayCreator<T> arrayCreator, int size) {
        super(arrayCreator, size);
    }
}