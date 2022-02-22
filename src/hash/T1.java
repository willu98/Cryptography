package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.CryptoTools;

public class T1 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		byte[] price = "123.55".getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(price);
		System.out.println(CryptoTools.bytesToHex(hash));
	}
}
