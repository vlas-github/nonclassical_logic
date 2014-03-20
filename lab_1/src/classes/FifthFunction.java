package classes;

public class FifthFunction extends FuzzySet{

	public FifthFunction(double ... paraparameters) {
		
		super(paraparameters);
	}

	@Override
	protected double calculatingLowerLimit(double alphaLevel) {

		double a = getParameters().get(0);
		double c = getParameters().get(2);
		
		if (alphaLevel == 0)
			return a;
		else if (alphaLevel > 0 && alphaLevel < 1)
			return alphaLevel * (c - a) + a;
		else return c;
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {
		
		double b = getParameters().get(1);
		double d = getParameters().get(3);
		
		if (alphaLevel == 1)
			return d;
		else if (alphaLevel > 0 && alphaLevel < 1)
			return -alphaLevel * (b - d) + b;
		else
			return b;

	}	
}
