package hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class P2 {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
		BigInteger eA = new BigInteger("1571");

		BigInteger pB = new BigInteger("98763457697834568934613");
		BigInteger qB = new BigInteger("8495789457893457345793");
		BigInteger nB = pB.multiply(qB);
		BigInteger eB = new BigInteger("87697");
		BigInteger phiB = pB.subtract(BigInteger.ONE).multiply(qB.subtract(BigInteger.ONE));
		BigInteger dB = eB.modInverse(phiB);
		
		BigInteger m_ = new BigInteger("418726553997094258577980055061305150940547956");
		BigInteger s_ = new BigInteger("749142649641548101520133634736865752883277237");
		
		
		BigInteger m = m_.modPow(dB, nB);
		BigInteger s = s_.modPow(dB, nB);

		//byte[] s = cipher.doFinal(s_.toByteArray());
		
		System.out.println("pt:" + m);
		System.out.println("s:" + s);
		
		BigInteger m2 = s.modPow(eA, nA);
		System.out.println("pt:" + m2);
		
	}
	
}
