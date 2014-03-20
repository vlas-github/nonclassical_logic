package classes;

public class SeventhFunction extends FuzzySet {

	public SeventhFunction(double ... paraparameters) {
		
		super(paraparameters);
	}

	@Override
	protected double calculatingLowerLimit(double alphaLevel) {
		
		double a = getParameters().get(0);
		double b = getParameters().get(1);

		return -Math.log(1 / alphaLevel - 1) / a + b;
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {
		
		return -1;
	}
	

}
