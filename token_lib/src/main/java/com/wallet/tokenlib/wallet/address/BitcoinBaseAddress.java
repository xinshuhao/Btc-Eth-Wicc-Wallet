package com.wallet.tokenlib.wallet.address;

import com.wallet.tokenlib.common.utils.NumericUtil;
import org.bitcoinj.core.*;

public class BitcoinBaseAddress implements BaseAddress {

    private NetworkParameters networkParameters;

    public BitcoinBaseAddress(NetworkParameters networkParameters) {
        this.networkParameters = networkParameters;
    }

    @Override
    public String fromMnemonic(String mnemonic) {
        return null;
    }

    @Override
    public String fromPrivateKey(String prvKeyHex) {
        ECKey key;
        if (prvKeyHex.length() == 51 || prvKeyHex.length() == 52) {
            DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParameters, prvKeyHex);
            key = dumpedPrivateKey.getKey();
        } else {
            key = ECKey.fromPrivate(NumericUtil.hexToBytes(prvKeyHex));
        }

        LegacyAddress address=LegacyAddress.fromPubKeyHash(this.networkParameters,key.getPubKeyHash());
        return address.toString();
    }

    @Override
    public String fromPrivateKey(byte[] prvKeyBytes) {
        ECKey key = ECKey.fromPrivate(prvKeyBytes);
        LegacyAddress address=LegacyAddress.fromPubKeyHash(this.networkParameters,key.getPubKeyHash());
        return address.toString();
    }
}
