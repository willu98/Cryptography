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

public class T2 {
    
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] ct = CryptoTools.hexToBytes("7AA38A029E773CBBC188A9FCEADAE99DA560B784C99AFEF2");
		byte[] ky = (CryptoTools.hexToBytes("4F75725269676874"));
		byte[] iv = CryptoTools.hexToBytes("496E566563746F72");
		
		
    	Key secret = new SecretKeySpec(ky, "DES");
    	Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
    	AlgorithmParameterSpec aps = new IvParameterSpec(iv);
    	cipher.init(Cipher.DECRYPT_MODE, secret);
    	
		System.out.println("CT :" + Arrays.toString(ct));
		
		
		
		
		//all three blocks of ct(8 bytes) 
		byte[] block1ct = Arrays.copyOfRange(ct, 0, 8);
		byte[] block2ct = Arrays.copyOfRange(ct, 8, 16);
		byte[] block3ct = Arrays.copyOfRange(ct, 16, 24);
		
		System.out.println("b1:" + Arrays.toString(block1ct));
		System.out.println("b2:" + Arrays.toString(block2ct));
		System.out.println("b3:" + Arrays.toString(block3ct));
		
		//tmp is used to store the resulting xor calculations from the ct blocks which will then be fed to DES/ECB/NoPadding
		byte[] tmp = new byte[ct.length];
		
		//getting tmp
		for (int i = 0; i < block1ct.length; i++) {
			tmp[i] = (byte) (block1ct[i] ^ iv[i]);
			tmp[i + 8] = (byte) (block2ct[i] ^ block1ct[i]);
			tmp[i + 16] = (byte) (block3ct[i] ^ block2ct[i]);
		}
		System.out.println("DES/ECB/NoPadding:" + Arrays.toString(tmp));
		System.out.println("tmp1:" + Arrays.toString(Arrays.copyOfRange(tmp, 0, 8)));
		System.out.println("tmp2:" + Arrays.toString(Arrays.copyOfRange(tmp, 8, 16)));
		System.out.println("tmp3:" + Arrays.toString(Arrays.copyOfRange(tmp, 16, 24)));		
		//plain text/result
		byte[] pt = cipher.doFinal(tmp);
		



		System.out.println("RES:"+new String(pt, StandardCharsets.UTF_8));
	}
}
