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

public class P1 {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
		byte[] ky = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
				
	    try {
	    	Key secret = new SecretKeySpec(ky, "AES");
	    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    	AlgorithmParameterSpec aps = new IvParameterSpec(iv);
	    	cipher.init(Cipher.DECRYPT_MODE, secret, aps);

	        byte[] pt = cipher.doFinal(ct);
	 
	        System.out.println(new String(pt, StandardCharsets.UTF_8));
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }	
		
	}

}
