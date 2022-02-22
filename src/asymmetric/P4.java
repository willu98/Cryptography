package asymmetric;

import java.math.BigInteger;

public class P4 {
	public static void main(String[] args) {
		BigInteger _one = new BigInteger("-1");
		BigInteger two = new BigInteger("2");
		BigInteger n = new BigInteger("1033931178476059651954862004553");
		BigInteger n_ = n.subtract(BigInteger.ONE);
		int primeCount = 0;
		int compCount = 0;
		
		int k = 0;
		while(n_.mod(two).equals(BigInteger.ZERO)) {
			n_ = n_.divide(two);
			k++;
		}
		System.out.println("K:" + k);
		n_ = n.subtract(BigInteger.ONE);
		//System.out.println(n_.toString());
		BigInteger m = n_.divide(two.pow(k));
	
		
		for (int a = 2; a < 10000; a++) {
			BigInteger b = new BigInteger(String.valueOf(a)).modPow(m, n);
			if(b.equals(BigInteger.ONE) || b.equals(n_)) {
				primeCount++;
				continue;
			}
			
			while(!(b.equals(BigInteger.ONE) || b.equals(n_))) {
				b = b.modPow(two, n);				
			}
			if(b.equals(n_)) primeCount++;
			else compCount++;					
		}
		System.out.println("PRIME COUNT:" + primeCount);
		System.out.println("COMP COUNT:" + compCount);
	}
}
