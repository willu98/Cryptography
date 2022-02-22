package foundation;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import util.CryptoTools;

public class C_Crypta {
	//QUESTION 3
	public static void main(String[] args) {
		byte[] bytes = null;
		int[] freq;
		double[] freq1 = new double[26];
		//READING FILE
		try {
			bytes = CryptoTools.fileToBytes("./data/MSG2.ct");					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		freq = CryptoTools.getFrequencies(bytes);
		//System.out.println(Arrays.toString(freq));
		for(int i = 0; i < freq.length; i++) {
			freq1[i] = (double)freq[i] / bytes.length;
		}
		System.out.println(Arrays.toString(freq1));
	
		//using key 
		for(int j = 0; j < bytes.length; j++) {
			bytes[j] = (byte) ((byte) ((Math.floorMod((bytes[j] - 'A') + 4, 26))) + 'A'); 		
		}
		//System.out.println(key);
		System.out.println(new String(bytes, StandardCharsets.UTF_8));		
	}
}
