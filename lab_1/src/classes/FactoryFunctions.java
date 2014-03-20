package classes;

public class FactoryFunctions {
	
	public static FuzzySet getFuzzySet(TypeFuzzySet type, double ... parameters) {
		
		switch (type) {
		case FirstFunction:
			return new FirstFunction(parameters);
		case SecondFunction:
			return new SecondFunction(parameters);
		case ThirdFunction:
			return new ThirdFunction(parameters);
		case FourthFunction:
			return new FourthFunction(parameters);
		case FifthFunction:
			return new FifthFunction(parameters);
		case SixthFunction:
			return new SixthFunction(parameters);	
		case SeventhFunction:
			return new SeventhFunction(parameters);
		case EighthFunction:
			return new Eighthfunction(parameters);
		default:
			return null;
		}
	}
}
