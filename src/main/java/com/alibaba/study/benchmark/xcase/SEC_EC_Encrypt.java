package com.alibaba.study.benchmark.xcase;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

public class SEC_EC_Encrypt extends BenchmarkCaseAdapter {
	private Cipher cipher;
	private byte[] plainBytes;

	public SEC_EC_Encrypt() {
		super("RSA-Encrypt");
	}

	public void init() throws Exception {
		plainBytes = new byte[32]; // 4k

		SecureRandom random = new SecureRandom();
		random.nextBytes(plainBytes);

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("ECDH", "BC");
		
		System.out.println(keyPairGen.getProvider().getName());
		
		keyPairGen.initialize(512);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();

		cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	}
	// 

	public void execute() throws Exception {
		cipher.doFinal(plainBytes);
	}
}
