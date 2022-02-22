package foundation;

import java.nio.charset.StandardCharsets;

import util.CryptoTools;

public class C_Exhaustive {
	//QUESTION2
	public static void main(String[] args) {
		byte[] bytes = null;
		double max = 0;
		int key = 0;
		
		//READING FILE
		try {
			bytes = "S KW GRY S KW".getBytes();					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//FINDING KEY
		for(int i = 0; i < 26; i++) {
			byte[] tmp = bytes.clone();
			double dot = 0;
			//System.out.println("***" + i + "************************************");
			//System.out.println(Arrays.toString(tmp));
			for(int j = 0; j < tmp.length; j++) {
				//System.out.println("---");
				//System.out.println(tmp[j]);
				tmp[j] = (byte) ((byte) ((Math.floorMod((tmp[j] - 'A') - i, 26))) + 'A');
				//System.out.println(tmp[j]);

			}
			//System.out.println(Arrays.toString(tmp));
			//System.out.println(new String(tmp, StandardCharsets.UTF_8));
			int[] freq = CryptoTools.getFrequencies(tmp);
			
			for(int k = 0; k < CryptoTools.ENGLISH.length; k++) {
				dot += freq[k] * CryptoTools.ENGLISH[k];
			}
			
			if(dot > max) {
				max = dot;
				key = i;
			}					
		}
		
		
		//using key 
		for(int j = 0; j < bytes.length; j++) {
			bytes[j] = (byte) ((byte) ((Math.floorMod((bytes[j] - 'A') - key, 26))) + 'A'); 		
		}
		//System.out.println(key);
		System.out.println(new String(bytes, StandardCharsets.UTF_8));
	}
}
