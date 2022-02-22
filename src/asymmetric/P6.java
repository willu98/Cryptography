package asymmetric;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import util.CryptoTools;

public class P6 {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		String alicePubKey = "3059301306072A8648CE3D020106082A8648CE3D0301070342000450C35C2FB11926C2C91E089CFC743F9D942EE14B8D42E25AE6588C4F93DDFF6ACDF520F74AF3E2500EF2A5E2C346D4DA7E92C1F89AD9FD4F3ED1B97DC3F39DC8";
		
		String alicePrivKey = "3041020100301306072A8648CE3D020106082A8648CE3D0301070427302502010104200FE89D3070EECF985F971851B088EC97605A08D037F3CF3463FED25BCE0037B5";
		
		String bobPubKey = "3059301306072A8648CE3D020106082A8648CE3D03010703420004678DF0E72D7FC86006174E506B1729081E5D1201936EBA8A39E8741E4F713F8C29AE2E62038D95B36A585E2A87FEA73BE482611115457A3D3823EA5D79E31154";
		String bobPrivKey = "3041020100301306072A8648CE3D020106082A8648CE3D030107042730250201010420090145EB296FD96158EDF5E59D20EBB8E7332BBE150784D91900DB2006980127";
		
		String ct = "B1803ED24B595CCB11AA39473DC7B10B";
		
		KeyFactory kf = KeyFactory.getInstance("EC");
		
		//generate public key and private keys for alice
		X509EncodedKeySpec pkAliceSpec = new X509EncodedKeySpec(CryptoTools.hexToBytes(alicePubKey));
		PublicKey alicePK = kf.generatePublic(pkAliceSpec);
		PKCS8EncodedKeySpec alicePrivSpec = new PKCS8EncodedKeySpec(CryptoTools.hexToBytes(alicePrivKey));
		PrivateKey alicePriv = kf.generatePrivate(alicePrivSpec);

		//bob pub and priv keys
		X509EncodedKeySpec pkBobSpec = new X509EncodedKeySpec(CryptoTools.hexToBytes(bobPubKey));
		PublicKey bobPK = kf.generatePublic(pkBobSpec);		
		PKCS8EncodedKeySpec bobPrivSpec = new PKCS8EncodedKeySpec(CryptoTools.hexToBytes(bobPrivKey));
		PrivateKey bobPriv = kf.generatePrivate(bobPrivSpec);
		
		// Perform key agreement
		KeyAgreement ka = KeyAgreement.getInstance("ECDH");
		ka.init(bobPriv);
		ka.doPhase(alicePK, true);
		byte[] sharedSecret = ka.generateSecret();
		
		ka.init(alicePriv);
		ka.doPhase(bobPK, true);
		byte[] sharedSecret2 = ka.generateSecret();
		System.out.println("Secret1:" + Arrays.toString(sharedSecret));
		System.out.println("Secret2:" + Arrays.toString(sharedSecret2));
		System.out.println("Secret(hex):" + CryptoTools.bytesToHex(sharedSecret2));

		
		byte[] iv = CryptoTools.hexToBytes("4000000001000000000C00000001000C");
		AlgorithmParameterSpec ivspec = new IvParameterSpec(iv);
		Key secret = new SecretKeySpec(sharedSecret, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, ivspec);
		byte[] tmp = cipher.doFinal(CryptoTools.hexToBytes(ct));
		
		System.out.println("PT:" + new String(tmp, StandardCharsets.UTF_8));
	}
}
