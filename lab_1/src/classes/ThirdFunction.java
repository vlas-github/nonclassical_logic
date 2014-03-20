package classes;

public class ThirdFunction extends FuzzySet {

	public ThirdFunction(double ... paraparameters) {
		
		super(paraparameters);
	}
	
	@Override
	protected double calculatingLowerLimit(double alphaLevel) {
		
		double a = getParameters().get(0);
		double c = getParameters().get(2);
		
		if (alphaLevel == 0)
			return a;
		else if (alphaLevel == 1)
			return c;
		else return alphaLevel * (c - a) + a;
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {
		
		double b = getParameters().get(1);
		double c = getParameters().get(2);
		
		if (alphaLevel == 0)
			return b;
		else if (alphaLevel == 1)
			return c;
		else return -alphaLevel * (b - c) + b;
	}

}
