package foundation;

import java.util.ArrayList;

import util.CryptoTools;

public class V_Crypta {
	public static void main(String[] args) {
		byte[] bytes = null;
		ArrayList<byte[]> segments= new ArrayList<>(); 
		
		//READING FILE
		try {
			bytes = CryptoTools.fileToBytes("./data/Q1.ct");				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//clean file
		bytes = CryptoTools.clean(bytes);
		
		//getting key length
		int keyLen = CryptoTools.getIC(bytes);
		System.out.println("KEY LENGTH: " + keyLen);
		
		//performing cryptnanalysis on on each k-length segment
		//this for loop gets each keylen segment and puts it into list
		for(int k = 0; k < keyLen; k++) {
			ArrayList<Byte> segment = new ArrayList<>();
			int pos = k;
			int j = 0;	
			while(pos < bytes.length) {				
				//System.out.println(ar[pos]);
				segment.add(bytes[pos]);
				pos += keyLen;
			}
			
			byte[] seg = new byte[segment.size()];
			for(int n = 0; n < segment.size(); n++) {
				seg[n] = segment.get(n).byteValue();
			}
			segments.add(seg);
		}
		
		//performing freq analysis on  each seg
		for(byte[] seg:segments) {
			double max = 0;
			int key = 0;
			//FINDING KEY FOR EACH SEGMENT
			for(int i = 0; i < 26; i++) {
				byte[] tmp = seg.clone();
				double dot = 0;

				for(int j = 0; j < tmp.length; j++) {
					tmp[j] = (byte) ((byte) ((Math.floorMod((tmp[j] - 'A') - i, 26))) + 'A');
				}

				int[] freq = CryptoTools.getFrequencies(tmp);
				
				for(int k = 0; k < CryptoTools.ENGLISH.length; k++) {
					dot += freq[k] * CryptoTools.ENGLISH[k];
				}
				
				if(dot > max) {
					max = dot;
					key = i;
				}					
			}
			System.out.println("KEY: " + key);
		}
		
		
	}
}
