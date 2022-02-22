package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import util.CryptoTools;

public class P3 {
	private static byte[] concat(byte[] arr1, byte[] arr2) {
		byte[] result = new byte[arr1.length + arr2.length];

		for(int i = 0; i < arr1.length; i++) {
			result[i] = arr1[i];
		}
		
		for(int i = 0; i < arr2.length; i++) {
			result[i + arr1.length] = arr2[i];
		}
		
		return result;
	}
	
	private static String HMAC (String oPad, String iPad, int blockSize, byte[] key, byte[] pt) throws NoSuchAlgorithmException {
		byte[] oKeyPad = new byte[blockSize];
		byte[] iKeyPad = new byte[blockSize];
		
		//o_key_pad ← key xor [0x5c  blockSize]    Outer padded key
	    //i_key_pad ← key xor [0x36  blockSize]    Inner padded key
		for(int i =0; i < oKeyPad.length; i++) {
			oKeyPad[i] = (byte)(key[i]^CryptoTools.hexToBytes(oPad)[0]);
			iKeyPad[i] = (byte)(key[i]^CryptoTools.hexToBytes(iPad)[0]);
		}
		/*
		System.out.println("New Key" + Arrays.toString(key));
		System.out.println("o_key_pad" + Arrays.toString(oKeyPad));
		System.out.println("i_key_pad" + Arrays.toString(iKeyPad));
		*/
		
		//i_key_pad ∥ message
		byte[] tmp = concat(iKeyPad, pt);
		//System.out.println(Arrays.toString(tmp));
		
		//hash(i_key_pad ∥ message)
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hash_tmp = md.digest(tmp);
		
		//o_key_pad ∥ hash(i_key_pad ∥ message)
		byte[] tmp2 = concat(oKeyPad, hash_tmp);
		
		//hash(o_key_pad ∥ hash(i_key_pad ∥ message))
		byte[] hash_tmp2 = md.digest(tmp2);
		
		return CryptoTools.bytesToHex(hash_tmp2);		
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		//PLAINTEXT
		//String m = "Why do tell actors to break a leg? because every play has a cast";
		//String m = "The quick brown fox jumps over the lazy dog";
		String m = "Mainly cloudy with 40 percent chance of showers";
		byte[] pt = m.getBytes();
		
		//SHA-1 KEY
		String K = "This is my secret key";
		//String K = "key";
		
		int blockSize = 64;
		
		byte[] key = K.getBytes();				
		
		// Keys longer than blockSize are shortened by hashing them
		if(key.length > blockSize) {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			key = md.digest(key);
		}	
		
		// Keys shorter than blockSize are padded to blockSize by padding with zeros on the right
		if(key.length < blockSize) {
			byte[] newKey = new byte[blockSize];
			for(int i = 0; i < key.length; i++) {
				newKey[i] = key[i];
			}
			
			key = newKey;
		}
		

		System.out.println("HMAC:");
		System.out.println(HMAC("5c", "36", blockSize, key, pt));
		
		/*System.out.println("PART B:");
		System.out.println(HMAC("36", "5c", blockSize, key, pt));*/		
	}
}
