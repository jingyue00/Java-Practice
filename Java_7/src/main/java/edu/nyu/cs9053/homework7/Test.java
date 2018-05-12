package edu.nyu.cs9053.homework7;

public class Test {
    public static void main(String[] args) {
        CryptoWallet<Bitcoin> cryptoWallet = new CryptoWallet<Bitcoin>(new BitcoinArrayCreator(), 2);
        cryptoWallet.add(new Bitcoin(3));
        cryptoWallet.add(new Bitcoin(4));
        cryptoWallet.add(new Bitcoin(5));
        CryptoWallet<Bitcoin> cryptoWallet1 = new CryptoWallet<Bitcoin>(new BitcoinArrayCreator(), 2);
        cryptoWallet1.add(new Bitcoin(1));
        cryptoWallet1.add(new Bitcoin(3));

        System.out.println(cryptoWallet.get(0).getAmount());
        cryptoWallet.remove(new Bitcoin(5));
//        for (int i = 0; i < cryptoWallet1.getSize(); i++) {
//            System.out.println(cryptoWallet1.get(i).getAmount());
//        }
//        CryptoWalletTransfer ct = new CryptoWalletTransfer();
//        ct.transfer(cryptoWallet, cryptoWallet1);
        for (int i = 0; i < cryptoWallet.getSize(); i++) {
            System.out.println(cryptoWallet.get(i).getAmount());
        }


    }
//        CryptoWallet<Cryptocurrency> cw = new CryptoWallet<Cryptocurrency>();
//        CryptoWallet<Cryptocurrency> cw2 = new CryptoWallet<Cryptocurrency>();
////        cw2.add(new Bitcoin(1.25));
////        cw2.add(new Bitcoin(3.45));
////        cw.add(new Bitcoin(1.25));
////        cw.add(new Bitcoin(3.45));
////        cw.add(new Bitcoin(3.55));
////        cw.add(new Bitcoin(3.15));
////        cw.add(new Bitcoin(3.25));
////        cw.add(new Bitcoin(3.56));
////        cw.add(new Bitcoin(3.57));
////        cw.add(new Bitcoin(3.58));
////        cw.add(new Bitcoin(3.59));
////        cw.add(new Bitcoin(3.60));
////        cw.add(new Bitcoin(3.61));
////        cw.add(new Bitcoin(3.61));
////        cw.add(new Bitcoin(3.62));
//        cw.add(new Bitcoin(1));
//        cw.add(new Bitcoin(2));
//        cw.add(new Bitcoin(3));
//        cw.add(new Bitcoin(4));
//        cw2.add(new Bitcoin(5));
////        cw2.add(new Bitcoin(4));
////        cw2.add(new Bitcoin(5));
////        cw2.add(new Bitcoin(6));
////        cw2.add(new Bitcoin(7));
//        System.out.println(cw.getSize());
//        for (int i = 0; i < cw.getSize(); i++) {
//            System.out.print(cw.get(i).getAmount()+", ");
//        }
//        System.out.println();
//        //System.out.println(cw.contains(new Bitcoin(3.58)));
//        //System.out.println(cw.get(1).getAmount());
//        //cw.remove(new Bitcoin(3.62));
//        for (int i = 0; i < cw2.getSize(); i++) {
//            System.out.print(cw2.get(i).getAmount()+", ");
//        }
//        //System.out.println(cw.contains(new Bitcoin(3.58)));
//        System.out.println();
//
////        System.out.println(cw.getSize());
////        cw.remove(new Bitcoin(1.25));
////        System.out.println(cw.get(14));
////        System.out.println(cw.getSize());
//        //System.out.println(cw.getSize());
//        System.out.println(cw2.getSize());
//        CryptoWalletTransfer ct = new CryptoWalletTransfer();
//        ct.transfer(cw, cw2);
//        //System.out.println(cw.getSize());
//        System.out.println(cw2.getSize());
//    }

}
