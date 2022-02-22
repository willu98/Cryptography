package symmetric;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class P2 {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
		//byte[] ct = CryptoTools.hexToBytes("FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236");
		byte[] m = "Why do we tell actors to break a leg? because every play has a cast".getBytes();
		byte[] ky = CryptoTools.hexToBytes("34567abcdef0321134567abcdef03211");
		byte[] iv = CryptoTools.hexToBytes("44668abddef1321134568abcdef13221");
				
	    try {
	    	Key secret = new SecretKeySpec(ky, "AES");
	    	Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
	    	AlgorithmParameterSpec aps = new IvParameterSpec(iv);
	    	cipher.init(Cipher.ENCRYPT_MODE, secret, aps);

	        byte[] pt = cipher.doFinal(m);
	 
	        System.out.println(new String(pt, StandardCharsets.UTF_8));
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }	
		
	}
}
