package com.wt.bitmap;

public class BitMap {

    private char[] bytes;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 8 + 1];
    }

    public void set(int k) {
        if (k > nbits)
            return;
        int byteIndex = k / 8;
        int bitIndex = k % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nbits)
            return false;
        int byteIndex = k / 8;
        int bitIndex = k % 8;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }
    
    public static void main(String[] args) {
        BitMap b = new BitMap(1000);
        b.set(200);
        b.set(201);
        b.set(1000);
        System.out.println(b.get(200));
        System.out.println(b.get(101));
    }
}
