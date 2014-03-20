package classes;

public class FourthFunction extends FuzzySet {

	public FourthFunction(double ... paraparameters) {
		
		super(paraparameters);
	}

	@Override
	protected double calculatingLowerLimit(double alphaLevel) {

		double a = getParameters().get(0);
		double b = getParameters().get(1);
		double c = getParameters().get(2);

		return c + Math.pow((1.0 / alphaLevel - 1) / a, 1.0 / b);
		
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {
		
		return -1;
	}

}
