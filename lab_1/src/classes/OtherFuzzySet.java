package classes;

import java.util.List;

public class OtherFuzzySet extends FuzzySet{

	public OtherFuzzySet(List<Expansion> expansion) {
		
		super(expansion);
	}
	
	
	@Override
	protected double calculatingLowerLimit(double alphaLevel) {
		return 0;
	}

	@Override
	protected double calculatingUpperLimit(double alphaLevel) {
		return 0;
	}

}
