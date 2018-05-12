package edu.nyu.cs9053.homework7;

public class BitcoinArrayCreator implements ArrayCreator<Bitcoin>{
    @Override
    public Bitcoin[] create(int size) {
        return new Bitcoin[size];
    }
}