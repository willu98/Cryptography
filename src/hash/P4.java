package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.CryptoTools;

public class P4 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String hash = "5ae9b7f211e23aac3df5f2b8f3b8eada";
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		//byte[] digest = md.digest(psw.getBytes());
		//System.out.println(CryptoTools.bytesToHex(digest));
		
		/*
		 * Q4: 
		 * A:not possible because by hashing the password, information is lost through the compression process of hashing
		 * B:it is possible to find preimage through a collision attack
		 * C: time complexity of 2^(n/2)
		 * D:crypto
		 * */
	}
}
