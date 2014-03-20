package classes;

public class SixthFunction extends FuzzySet {

	public SixthFunction(double ... paraparameters) {
		
		super(paraparameters);
	}

	@Override
	protected double calculatingLowerLimit(double alphaLevel) {
		
		double a = getParameters().get(0);
		double b = getParameters().get(1);
		
		return Math.sqrt(-2.0 * b * b  * Math.log(alphaLevel)) + a;
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {

		double a = getParameters().get(0);
		double b = getParameters().get(1);
		
		return -Math.sqrt(-2.0 * b * b  * Math.log(alphaLevel)) + a;
	}

	
}
