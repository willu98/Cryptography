package foundation;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import util.CryptoTools;

public class C_Encrypt {
	private static String cCipher(int key) {
		//QUESTION 1
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get("./data/MSG1.clean")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		char[] tmp = content.toCharArray();
		for(int i = 0; i < tmp.length; i++) {
			//System.out.println("----");
			//System.out.println(tmp[i]);
			int tmp2 = (((tmp[i] - 'A') + key)%26) + 'A';
			tmp[i] = (char)tmp2;
			//System.out.println(tmp[i]);
		}
		content = new String(tmp);
		return content;		
	}
	
	
	public static void main(String[] args) {
		byte[] bytes = null;
		try {
			byte[]tmp = CryptoTools.fileToBytes("./data/MSG1.txt");					
			bytes = CryptoTools.clean(tmp);
			CryptoTools.bytesToFile(bytes, "./data/MSG1.clean");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cCipher(19));
	}
}
