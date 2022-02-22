package symmetric;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class C1 {

	private static String otp(String key, String text) {
		char[] tmp = new char[text.length()];
		for(int i = 0; i < tmp.length; i++){
			tmp[i] = (char) (Math.floorMod(text.charAt(i) + key.charAt(i) - 2 * 'A', 26) + 'A');
		}
		
		return new String(tmp);
	}
	
	private static String decryptOtp(String key, String text) {
		char[] tmp = new char[text.length()];
		for(int i = 0; i < tmp.length; i++){
			tmp[i] = (char) (Math.floorMod(text.charAt(i) - key.charAt(i) - 2 * 'A', 26) + 'A');
		}
		
		return new String(tmp);
	}
	
	private static String getKey(String ct, String pt) {
		char[]tmp = new char[ct.length()];
		for(int i = 0; i < tmp.length; i++){
			tmp[i] = (char) (Math.floorMod(ct.charAt(i) - pt.charAt(i) - 2 * 'A', 26) + 'A');
		}		
		return new String(tmp);
	}
	
	public static void main(String[] args) {
		String content = "SENDMOREMONEY";
		
		String key = "JABHXPVOLLCIJ";
		
		
		String ct = otp(key, content);
		
		System.out.println(ct);
		
		String k2 = getKey(ct, "CASHNOTNEEDED");
		System.out.println(k2);
		String pt = decryptOtp(k2, ct);
		System.out.println(pt);

	}
	
	
}
