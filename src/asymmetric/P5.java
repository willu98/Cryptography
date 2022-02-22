package asymmetric;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.CryptoTools;

public class P5 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		BigInteger p = new BigInteger("341769842231234673709819975074677605139");
		BigInteger g = new BigInteger("37186859139075205179672162892481226795");
		BigInteger aX = new BigInteger("83986164647417479907629397738411168307");
		BigInteger bX = new BigInteger("140479748264028247931575653178988397140");
		
		BigInteger pubA = g.modPow(aX, p);
		BigInteger pubB = g.modPow(bX, p);
		
		BigInteger kPriv = pubB.modPow(aX, p);
		System.out.println(kPriv.toString(16));
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hash = md.digest(CryptoTools.hexToBytes((kPriv.add(new BigInteger("12345")).toString(16))));
		System.out.println(CryptoTools.bytesToHex(hash));
	}
}
