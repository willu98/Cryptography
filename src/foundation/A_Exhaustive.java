package foundation;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import util.CryptoTools;

public class A_Exhaustive {
	//QUESTION 4	
	private static final int[] ALPHA = {1,3,5,7,9,11,15,17,19,21,23,25};
	
	private static double max = 0;
	private static int[] keys = new int[2];
	private static byte[] result;
	
	public static void main(String[] args) {
		byte[] bytes = "IRIYFCGBLROSCOVCAWCAVILVIAIBFCBSAGWIGBIIRAIACDIOAUVCFCBSGVGFXIVCBFIRRIYFKORLVGLIVFEOAEGKVGUBOBDCAOBGFXIVNGVWGNOYODIWCYDCAXGBIAFECBOBEOBEKAIGNFXIUGVMGNGFXIVAUXIFXIVLKTRCAXIDKBLKTRCAXIDGVLGAFIDIRIYFVGBCYORREISGBUITACFIAOFFVCTKFIDGVOBGBEWGKAWKAFCBYRKDILVGLIVOYMBGURIDSIWIBFEGKYOBNCBDNKRRDINCBCFCGBAGNLROSCOVCAWOBDGFXIVNGVWAGNYGBDKYFFXOFOVIVISOVDIDOAAIVCGKAOYODIWCYGNNIBYIACBEGVMAAIBOFILGRCYEGBOYODIWCYXGBIAFEYGWWGBFELIAGNLROSCOVCAWLROSCOVCAWYOBFOMIWOBENGVWAAGWIGNFXIWGAFYGWWGBFELIAGNLROSCOVCAWCBYRKDIDGUBRGODCBSGVTKECBSVIAIOVYXLOLIVADGUBRGODCBSONVIILOLIVNVGWOUITACFIGVLOECBSFGDGUBRGODOLOLIVOBDAKTWCFFCBSCFOAEGKVGUBUGVMYGLECBSOBDLOAFCBSYGLECBSOBDLOAFCBSLGVFCGBAGNFIZFNVGWGBRCBIHGKVBOROVFCYRIAGVUITACFIAUCFXGKFLVGLIVYCFOFCGBYGLECBSGVAKTWCFFCBSAGWIGBIIRAIAUGVMYGLECBSOLOLIVROTVILGVFNGVWKRODIACSBYGWLKFIVYGDIWKACYYXGVIGSVOLXEOAACSBWIBFIFYOBDAKTWCFFCBSCFOAEGKVGUBUGVM".getBytes();					
		
		
		for(int b = 0; b < 26; b++) {
			for(int a: ALPHA) {
				byte[] tmp = bytes.clone();
				double dot = 0;
				
				for(int i = 0; i < tmp.length; i++) {
					
					BigInteger aInverse = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26));
					tmp[i] = (byte)(Math.floorMod(((Math.floorMod((tmp[i] - 'A') - b, 26) + 'A') * (aInverse.intValue())), 26) + 'A');
					if(tmp[i] < 0) {System.out.println(a + b + ":" + tmp[i]);}
				}
				//System.out.println(Arrays.toString(tmp));
				int[] freq = CryptoTools.getFrequencies(tmp);
				for(int i = 0; i < CryptoTools.ENGLISH.length; i++) {
					dot += freq[i] * CryptoTools.ENGLISH[i];
				}
				
				if(dot > max) {
					max = dot;
					keys[0] = a;
					keys[1] = b;
					result = tmp;
				}	
				
				//System.out.println("a:" + a + " b:" + b + "dot: " + dot +  "=>" + new String(tmp, StandardCharsets.UTF_8));//new String(tmp));
			}
		}
		System.out.println("RES: " + "a:" + keys[0] + " b:" + keys[1] + " dot: " + max +  "=>\n" + new String(result, StandardCharsets.UTF_8));
		System.out.println(new String(bytes, StandardCharsets.UTF_8));
	}
}
