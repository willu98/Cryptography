package symmetric;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class P5 {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] ct = CryptoTools.hexToBytes("8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284");
		byte[] key = "FACEBOOK".getBytes();
		byte[] keyNeg = new byte[key.length];
		
		for (int i = 0; i < keyNeg.length; i++) {
			keyNeg[i] = (byte) (key[i]^~0);
		}
		

    	Key secret1 = new SecretKeySpec(key, "DES");
    	Key secret2 = new SecretKeySpec(keyNeg, "DES");
    	Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
    	cipher.init(Cipher.DECRYPT_MODE, secret2);
    	byte[] tmp = cipher.doFinal(ct);
    	cipher.init(Cipher.DECRYPT_MODE, secret1);
    	byte[] pt = cipher.doFinal(tmp);
		System.out.println(new String(pt, StandardCharsets.UTF_8));

	}
}
