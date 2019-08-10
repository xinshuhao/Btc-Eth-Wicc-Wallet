package com.wallet.tokenlib.wallet.address;

import com.wallet.tokenlib.common.exception.Messages;
import com.wallet.tokenlib.common.exception.TokenException;
import com.wallet.tokenlib.wallet.model.ChainType;
import com.wallet.tokenlib.wallet.model.Metadata;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

public class AddressCreator {

    public static BaseAddress getInstance(String type, boolean isMainnet, String segWit) {
        if (ChainType.ETHEREUM.equals(type)) {
            return new EthBaseAddress();
        } else if (ChainType.BITCOIN.equals(type)) {

            NetworkParameters network = isMainnet ? MainNetParams.get() : TestNet3Params.get();
            if (Metadata.P2WPKH.equals(segWit)) {
                return new SegWitBitcoinBaseAddress(network);
            }
            return new BitcoinBaseAddress(network);
        } else if (ChainType.WICC.equals(type)) {
            NetworkParameters network = isMainnet ? MainNetParams.get() : TestNet3Params.get();
            return new WiccBaseAddress();
        }else {
            throw new TokenException(Messages.WALLET_INVALID_TYPE);
        }
    }
}
