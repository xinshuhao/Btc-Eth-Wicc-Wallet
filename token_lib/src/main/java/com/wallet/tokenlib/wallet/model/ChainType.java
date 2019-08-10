package com.wallet.tokenlib.wallet.model;

import com.wallet.tokenlib.common.exception.Messages;
import com.wallet.tokenlib.common.exception.TokenException;

public class   ChainType {
    public final static String ETHEREUM = "ETHEREUM";
    public final static String BITCOIN = "BITCOIN";
    public final static String WICC = "WICC";

    public static void validate(String type) {
        if (!ETHEREUM.equals(type) && !BITCOIN.equals(type) && !WICC.equals(type)) {
            throw new TokenException(Messages.WALLET_INVALID_TYPE);
        }
    }
}
