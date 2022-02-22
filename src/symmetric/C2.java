package symmetric;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import util.CryptoTools;

public class C2 {
	private static byte[] getKey(byte[] ct2, byte[] ct3) {
		byte[]tmp = new byte[ct2.length];
		for(int i = 0; i < tmp.length; i++){
			tmp[i] = (byte) (Math.floorMod(ct2[i] - ct3[i] - 2 * 'A', 26) + 'A');
		}		
		return tmp;
	}
	
	private static byte[] decryptOtp(byte[] ct1, byte[] key) {
		byte[] tmp = new byte[ct1.length];
		for(int i = 0; i < tmp.length; i++){
			tmp[i] = (byte) (ct1[i] ^ key[i]);
		}
		
		return tmp;
	}
	
	public static void main(String[] args) {
		byte[] cipherTextAlice1 = CryptoTools.hexToBytes("0A4F0C08003503492F247442105B5757");
		byte[] cipherTextBob = CryptoTools.hexToBytes("5E2769286B507A69494B066252343579");
		byte[] cipherTextAlice2 = CryptoTools.hexToBytes("170708454B1116002A2E2111725F5000");
		byte[] key = new byte[cipherTextBob.length];
		
		for(int i = 0; i < key.length; i++) {
			key[i] = (byte)(cipherTextBob[i]^cipherTextAlice2[i]);
		}
		/*String[] cipherTextsHex = {"3D48044D421349564A1541054204131C", "3D54024D531442454C0941175404150A"};
		
		byte[] ct1 = CryptoTools.hexToBytes(cipherTextsHex[0]);
		byte[] ct2 = CryptoTools.hexToBytes(cipherTextsHex[1]);
		System.out.println(Arrays.toString(ct1));		
		System.out.println(Arrays.toString(ct2));
		
		//bridge written in byte form
		byte[] bridge = new byte[]{98,114,105,100,103,101};
		
		for(int i = 0; i < 11; i++) {
			//getting possible position for bridge
			byte[] tmp= Arrays.copyOfRange(ct1, i, i + 6);
			
			//tmp key created
			byte[] tmpkey = new byte[tmp.length];
			
			//
			for(int k =0; k < tmp.length; k++) {
				tmpkey[k] = (byte) (tmp[i]^bridge[i]);
			}
		}*/
		System.out.println(Arrays.toString(cipherTextAlice1));
		System.out.println(Arrays.toString(cipherTextBob));
		System.out.println(Arrays.toString(cipherTextAlice2));
		System.out.println(Arrays.toString(key));
		System.out.println(new String(decryptOtp(cipherTextAlice1, key), StandardCharsets.UTF_8));
		//Meet me in Las1002
	}
}
