package hygedistr_test1_1;

import org.apache.commons.math3.distribution.HypergeometricDistribution;

public class hygedistr_test1_1_invoke {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Hey guys!");

		HypergeometricDistribution hyged = new HypergeometricDistribution(50,20,10);
		
		System.out.println(hyged.upperCumulativeProbability(5));
		
	}

}
