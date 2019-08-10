package com.wallet.tokenlib.address;

import com.wallet.tokenlib.wallet.address.AddressCreator;
import com.wallet.tokenlib.wallet.address.BaseAddress;
import com.wallet.tokenlib.wallet.model.ChainType;
import com.wallet.tokenlib.wallet.model.Metadata;
import org.junit.Test;

public class AddressTest {
    public static final String BTC_PRIVATE_KEY_STRING =
            "L2hfzPyVC1jWH7n2QLTe7tVTb6btg9smp5UVzhEBxLYaSFF7sCZB";
    public static final String BTC_PRIVATE_KEY_TESTNET_WIF = "cT4fTJyLd5RmSZFHnkGmVCzXDKuJLbyTt7cy77ghTTCagzNdPH1j";
    public static final String ETH_PRIVATE_KEY_STRING =
            "a392604efc2fad9c0b3da43b5f698a2e3f270f170d859912be0d54742275c5f6";
    @Test
    public void generateBtcAddress() {
        BaseAddress mainCreator =AddressCreator.getInstance(ChainType.BITCOIN, true, Metadata.NONE);
        String mainAddress=mainCreator.fromPrivateKey(BTC_PRIVATE_KEY_STRING);
        System.out.println(mainAddress);

        BaseAddress testcreator =AddressCreator.getInstance(ChainType.BITCOIN, false, Metadata.NONE);
        String testAddress=testcreator.fromPrivateKey(BTC_PRIVATE_KEY_TESTNET_WIF);
        System.out.println(testAddress);
    }

    @Test
    public void generateBTCP2WPKHAddress() {
        BaseAddress mainCreator =AddressCreator.getInstance(ChainType.BITCOIN, true, Metadata.P2WPKH);
        String mainAddress=mainCreator.fromPrivateKey(BTC_PRIVATE_KEY_STRING);
        System.out.println(mainAddress);

        BaseAddress testCreator =AddressCreator.getInstance(ChainType.BITCOIN, false, Metadata.P2WPKH);
        String testAddress=testCreator.fromPrivateKey(BTC_PRIVATE_KEY_TESTNET_WIF);
        System.out.println(testAddress);
    }

    @Test
    public void generateEthAddress() {

    }
}