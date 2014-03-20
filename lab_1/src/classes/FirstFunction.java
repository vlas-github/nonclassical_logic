package classes;

public class FirstFunction extends FuzzySet {

	public FirstFunction(double ... paraparameters) {
		
		super(paraparameters);
	}
	
	double calcFirstFunction(double alphaLevel, double a, double b) {
		
		if (alphaLevel < 0.5)
			return a + Math.sqrt(alphaLevel * Math.pow(b - a, 2) / 2);
		else if (alphaLevel == 0.5)
			return (a + b) / 2;
		else
			return b -  Math.sqrt((1 - alphaLevel) * Math.pow(b - a, 2) / 2);
	}
	
	@Override
	protected double calculatingLowerLimit(double alphaLevel) {
		
		double a = getParameters().get(0);
		double b = getParameters().get(1);		

		return calcFirstFunction(alphaLevel, a, b);
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {
		
		return -1;
	}

}
