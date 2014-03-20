package classes;

public class Expansion {

	private double alphaLevel;
	private double upperLimit; 
	private double lowerLimit;

	public Expansion(double alphaLevel, double lowerLimit, double upperLimit) {

		this.alphaLevel = alphaLevel;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
	}
	
	public double getAlphaLevel() {
		return alphaLevel;
	}
	public void setAlphaLevel(double alphaLevel) {
		this.alphaLevel = alphaLevel;
	}
	public double getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}
	public double getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}	
}
