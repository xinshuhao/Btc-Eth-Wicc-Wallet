package com.wallet.tokenlib.wallet.address;

import com.wallet.tokenlib.common.exception.Messages;
import com.wallet.tokenlib.common.exception.TokenException;
import com.wallet.tokenlib.common.utils.NumericUtil;
import org.bitcoinj.core.*;

public class SegWitBitcoinBaseAddress implements BaseAddress {
    private NetworkParameters networkParameters;

    public SegWitBitcoinBaseAddress(NetworkParameters networkParameters) {
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
            if (!key.isCompressed()) {
                throw new TokenException(Messages.SEGWIT_NEEDS_COMPRESS_PUBLIC_KEY);
            }
        } else {
            key = ECKey.fromPrivate(NumericUtil.hexToBytes(prvKeyHex), true);
        }
        return calcSegWitAddress(key.getPubKeyHash());
    }

    @Override
    public String fromPrivateKey(byte[] prvKeyBytes) {
        ECKey key = ECKey.fromPrivate(prvKeyBytes, true);
        return calcSegWitAddress(key.getPubKeyHash());
    }

    private String calcSegWitAddress(byte[] pubKeyHash) {
        String redeemScript = String.format("0x0014%s", NumericUtil.bytesToHex(pubKeyHash));
        return LegacyAddress.fromScriptHash(networkParameters, Utils.sha256hash160(NumericUtil.hexToBytes(redeemScript))).toBase58();
       // return LegacyAddress.fromP2SHHash(networkParameters, Utils.sha256hash160(NumericUtil.hexToBytes(redeemScript))).toBase58();
    }

    public Address fromPrivateKey(ECKey ecKey) {
        String redeemScript = String.format("0x0014%s", NumericUtil.bytesToHex(ecKey.getPubKeyHash()));
        return LegacyAddress.fromScriptHash(networkParameters, Utils.sha256hash160(NumericUtil.hexToBytes(redeemScript)));
      //  return LegacyAddress.fromP2SHHash(networkParameters, Utils.sha256hash160(NumericUtil.hexToBytes(redeemScript)));
    }
}
