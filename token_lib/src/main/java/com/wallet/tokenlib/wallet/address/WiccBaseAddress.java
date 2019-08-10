package com.wallet.tokenlib.wallet.address;

public class WiccBaseAddress implements BaseAddress {
    @Override
    public String fromMnemonic(String mnemonic) {
        return null;
    }

    @Override
    public String fromPrivateKey(String prvKeyHex) {
        return null;
    }

    @Override
    public String fromPrivateKey(byte[] prvKeyBytes) {
        return null;
    }
}
