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

public class P4 {  
    
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] ct = CryptoTools.hexToBytes("437DBAB5607137A5CFC1031114634087");
		byte[] ky = (CryptoTools.hexToBytes("6B79466F724D4F50"));
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
    	Key secret = new SecretKeySpec(ky, "DES");
    	Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
    	AlgorithmParameterSpec aps = new IvParameterSpec(iv);
    	cipher.init(Cipher.DECRYPT_MODE, secret);
    	byte[] tmp = cipher.doFinal(ct);
    	
		System.out.println("CT :" + Arrays.toString(ct));
		System.out.println("TMP:" + Arrays.toString(tmp));
		
		//1st block of ct(8 bytes) and negated version
		byte[] block1ct = Arrays.copyOfRange(ct, 0, 8);
		byte[] block1ctNeg = new byte[block1ct.length];
		
		//1st cipher block dencrypted
		byte[] block1ptPrime = Arrays.copyOfRange(tmp, 0, 8);
		
		//2nd cipher block dencrypted 
		byte[] block2ptPrime = Arrays.copyOfRange(tmp, 8, tmp.length);
		
		//negative for iv
		byte[] ivNeg = new byte[iv.length];
		
		//plain text/result
		byte[] pt= new byte[tmp.length];
		
		//negating block1
		for(int i = 0; i < block1ct.length; i++) {
			block1ctNeg[i] = (byte) (block1ct[i]^~0);
			ivNeg[i] = (byte) (iv[i]^~0);
		}
		
		System.out.println("b1:" + Arrays.toString(block1ct));
		System.out.println("b1N:" + Arrays.toString(block1ctNeg));
		System.out.println("b2:" + Arrays.toString(block2ptPrime));

		//getting pt
		for (int i = 0; i < block1ct.length; i++) {
			pt[i] = (byte) (ivNeg[i] ^ block1ptPrime[i]);
			pt[i + 8] = (byte) (block1ctNeg[i] ^ block2ptPrime[i]);
		}
		System.out.println(new String(pt, StandardCharsets.UTF_8));
		/*for (int i = ct.length - 1; i >= 1; i--) {
	        byte negation = (byte) (ct[i - 1]^~0);
	        
	        System.out.println("CT:"+ct[i-1]+":"+CryptoTools.bytesToBin(new byte []{ct[i - 1]}));
	        System.out.println("neg:"+negation + ":" + CryptoTools.bytesToBin(new byte []{negation}));
	        System.out.println("tmp:"+tmp[i] + ":" + CryptoTools.bytesToBin(new byte []{tmp[i]}));
	        
	        pt[i] = (byte) (negation ^ tmp[i]);
	        
	        System.out.println("PT:"+pt[i]+":"+CryptoTools.bytesToBin(new byte []{pt[i]}));
		}
			
		byte []ivNegation = new byte[iv.length];
		String ivNegStr = CryptoTools.bytesToBin(iv).replace('0', '2').replace('1', '0').replace('2', '1');			
		System.out.println(CryptoTools.bytesToBin(iv));		
		System.out.println(ivNegStr);*/
	}
}
