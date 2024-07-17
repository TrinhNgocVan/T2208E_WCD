package org.example.utils.encryption;

public enum AESKeySize {
    SIZE_128(128), SIZE_192(192),SIZE_256(256);
    private final int val;

    AESKeySize(final int i) {
        this.val = i;
    }
    public int getVal(){
        return val;
    }
}
