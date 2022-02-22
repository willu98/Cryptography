package symmetric;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class C3 {
	private static byte binToByte(char[] txt) {
		//System.out.println(new String(txt));
		return Byte.parseByte(new String(txt), 2);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] pt = "Facebook".getBytes();
		byte[] ky = "universe".getBytes();
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct1 = cipher.doFinal(pt);		
		
		
		Random random = new Random();

		int bit = random.nextInt(63);
		//System.out.println(bit);
		char[] ptBinCharArr = CryptoTools.bytesToBin(pt).toCharArray();
		System.out.println("PT1:" + new String(ptBinCharArr));
		int count = 0;
		for(int k = 0; k < 12; k++) {
			ptBinCharArr[bit] = (char) (Math.floorMod(ptBinCharArr[bit] - '0' + 1, 2) + '0');
			//System.out.println("PT2:" + new String(ptBinCharArr));
			
			byte[] tmp = new byte[8];
			for(int i = 0; i <  8; i++) {
				Byte b = binToByte(Arrays.copyOfRange(ptBinCharArr, i *  8, i * 8 + 8));
				//System.out.println(b);
				tmp[i] = b;
			}
			//System.out.println(new String(tmp, StandardCharsets.UTF_8));
			byte[] ct2 = cipher.doFinal(tmp);
			System.out.println("CT1:" + CryptoTools.bytesToBin(ct1));
			System.out.println("CT2:" + CryptoTools.bytesToBin(ct2));
	
			
			
			for(int i = 0; i < tmp.length; i++){
				if(ct2[i] != ct1[i]) count++;
			}
		}
		System.out.println(count/12);
	}

}
