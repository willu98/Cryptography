package hash;

public class P5 {
	public static void main(String[] args) {
		double s = 1;
		int n = 1;
		double n1 = 1.177*Math.sqrt(365);
		for(int i = 1; i < n - 1; i++) {
			s *= ( 1 - (double)1/365);
		}
		s = 1- s;
		
		double s2 = 1 - Math.pow(Math.E, (-n1 * n1) / (730));
		System.out.println(s2);
		/*
		 * 
		 * */
		
	}
}
