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

public class P3 {
	
	/*
	 * not possible because CBC mode of encryption is such that prior blocks determine the encryption of later blocks, as such decryption aslso depends on the missing blocks and hence not possible*/
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] ct = CryptoTools.hexToBytes("4E51297B424F90D8B2ACD6ADF010DDC4");
		byte[] ky = ("CSE@YORK".getBytes());
		byte[] iv = CryptoTools.hexToBytes("0123456701234567");
				
	    try {
	    	Key secret = new SecretKeySpec(ky, "DES");
	    	Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
	    	AlgorithmParameterSpec aps = new IvParameterSpec(iv);
	    	cipher.init(Cipher.DECRYPT_MODE, secret, aps);

	        byte[] pt = cipher.doFinal(ct);
	 
	        System.out.println(Arrays.toString(pt));//new String(pt, StandardCharsets.UTF_8));
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }	
		
	}
}
