package com.icarexm.jiediuser.utils;

import java.security.Provider;

public class CryptoProvider extends Provider {
    public CryptoProvider() {
        super("Crypto", 1.0D, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
        this.put("SecureRandom.SHA1PRNG", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
        this.put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
    }
}
