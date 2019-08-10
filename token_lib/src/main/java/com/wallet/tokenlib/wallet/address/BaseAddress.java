package com.wallet.tokenlib.wallet.address;

public interface BaseAddress {
    String fromMnemonic(String mnemonic);
    String fromPrivateKey(String prvKeyHex);
    String fromPrivateKey(byte[] prvKeyBytes);
}
