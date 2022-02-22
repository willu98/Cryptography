package key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Q5 {
/*	public static void main(String[] args) {
		int m = 1234;
		int n = 6;
		int t = 3;
		int degree = t - 1;
		
		ArrayList<Integer> coefficients = new ArrayList<>();
		coefficients.add(m);
		
		System.out.println("t - 1 randomly generated values");
		for(int i = 0; i < degree; i++) {
			Random rand = new Random();
			coefficients.add(rand.nextInt(1000));	
		}
		//printing out randomly generated coefficients
		System.out.println(coefficients);
		
		ArrayList<Polynomial> polynomials = new ArrayList<>();
		for(int i = coefficients.size() - 1; i >= 0; i--) {
			Polynomial poly = new Polynomial(coefficients.get(i), i);
			polynomials.add(poly);
		}
		
		Polynomial poly = new Polynomial(0,0);
		for(Polynomial p: polynomials) {
			poly = poly.plus(p);
		}
		
		System.out.println("-----");
		System.out.println("polynomial of degree (t - 1)");
		//printing out computed polynomials and final polynomial
		//System.out.println(polynomials);
		System.out.println(poly);
		System.out.println("-----");
		System.out.println("n randomly generated points");
		HashMap<Integer, Integer> points = new HashMap<>();
		for(int x = 1; x <= n; x++) {
			int y = poly.evaluate(x);
			points.put(x, y);
			//printing out points
			System.out.println("(" + x +"," + points.get(x) + ")");
		}
		
		System.out.println("-----");
		System.out.println("t points used to obtain secret");
		int[] xVals = {2,3,5};
		for(int i = 0; i < xVals.length; i++) {
			System.out.println("(" + xVals[i] +"," + points.get(xVals[i]) + ")");
		}
		
		
		double secretDouble = 0;
		
		//simplified version of Lagrange polynomial interpolation to retrieve only secret
		//[-Xm/(Xj - Xm)] * [-Xm+1/(Xj - Xm+1)]*...
		for(int j = 0; j < t; j++) {
			double secretTmp = 1;
			for(int i = 0; i < t; i++) {
				if(j == i) continue;
				double coeff = ((-1.0) * xVals[i]) / (xVals[j] - xVals[i]); 
				secretTmp *= coeff;
			}
			secretTmp *= points.get(xVals[j]);
			secretDouble += secretTmp;
		}
		
		System.out.println("-----");
		System.out.println("secret");
		System.out.println((int)secretDouble);
	}*/
	
	public static void main(String[] args) {

		int n = 6;

		int t = 2;


		HashMap<Integer, Integer> points = new HashMap<>();

		points.put(1,  172);

		points.put(2,  223);

		points.put(3,  274);

		points.put(4,  326);

		System.out.println("-----");

		System.out.println("t points used to obtain secret");

		int[] xVals = {3,4};

		for(int i = 0; i < xVals.length; i++) {

			System.out.println("(" + xVals[i] +"," + points.get(xVals[i]) + ")");

		}

				

		double secretDouble = 0;

		

		//simplified version of Lagrange polynomial interpolation to retrieve only secret

		//[-Xm/(Xj - Xm)] * [-Xm+1/(Xj - Xm+1)]*...

		for(int j = 0; j < t; j++) {

			double secretTmp = 1;

			for(int i = 0; i < t; i++) {

				if(j == i) continue;

				double coeff = ((-1.0) * xVals[i]) / (xVals[j] - xVals[i]); 

				secretTmp *= coeff;

			}

			secretTmp *= points.get(xVals[j]);

			secretDouble += secretTmp;

		}

		

		System.out.println("-----");

		System.out.println("secret");

		System.out.println((int)secretDouble);

	}	
}
