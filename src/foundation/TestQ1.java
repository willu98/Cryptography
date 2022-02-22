package foundation;

import java.util.ArrayList;

import util.CryptoTools;

public class TestQ1 {
	public static void main(String[] args) {
		byte[] bytes = null;
		ArrayList<byte[]> segments= new ArrayList<>(); 
		
		//READING FILE
		try {
			bytes = CryptoTools.fileToBytes("./data/Q3.ct");				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//clean file
		bytes = CryptoTools.clean(bytes);
		
		//getting key length
		int keyLen = CryptoTools.getIC(bytes);
		System.out.println("KEY LENGTH: " + keyLen);
	}
}
