package classes;

public class SecondFunction extends FuzzySet {

	public SecondFunction(double ... paraparameters) {
		
		super(paraparameters);
	}
	
	@Override
	protected double calculatingLowerLimit(double alphaLevel) {
		
		double a = getParameters().get(0);
		double b = getParameters().get(1);	
		
		if (alphaLevel < 0.5)
			return a + Math.sqrt(alphaLevel * Math.pow(b - a, 2) / 2);
		else if (alphaLevel == 0.5)
			return (a + b) / 2;
		else
			return b - Math.sqrt((1 - alphaLevel) * Math.pow(b - a, 2) / 2);
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {

		double a = getParameters().get(0);
		double b = getParameters().get(1);
		double c = getParameters().get(2);
		
		if (alphaLevel < 0.5)
			return c + b - (a + Math.sqrt(alphaLevel * Math.pow(b - a, 2) / 2));
		else if (alphaLevel == 0.5)
			return (a + b) / 2;
		else
			return c + b - (b - Math.sqrt((1 - alphaLevel) * Math.pow(b - a, 2) / 2));
	}

}
