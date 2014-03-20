package classes;

public class Eighthfunction extends FuzzySet {

	public Eighthfunction(double ... paraparameters) {
		
		super(paraparameters);
	}

	@Override
	protected double calculatingLowerLimit(double alphaLevel) {

		double a = getParameters().get(0);
		double b = getParameters().get(1);
		
		return -Math.sqrt(-2.0 * b * b  * Math.log(alphaLevel)) + a;
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {

		double a = getParameters().get(0);
		double b = getParameters().get(2);
		
		return Math.sqrt(-2.0 * b * b  * Math.log(alphaLevel)) + a;
	}

}
