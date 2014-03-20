package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class FuzzySet {

	private static int countExpansionLeavels = 100;
	
	private List<Expansion> 	expansion;
	private List<Double> 		parameters;
	private Color 				color;
	
	public Color getColor() {
		
		return color;
	}

	public void setColor(Color color) {
		
		this.color = color;
	}

	public FuzzySet(List<Expansion> expansion) {
		
		this.expansion = expansion;
	}
	
	public FuzzySet(double ... parameters) {

		this.parameters = new ArrayList<Double>();
		
		for(double d : parameters)	
			this.parameters.add(d);
		
		this.expansion = new ArrayList<Expansion>();
		
		double alphaLevel = 1.0 / countExpansionLeavels;
		
		for(int i = 0; i < countExpansionLeavels - 1; i++) {
			
			expansion.add(new Expansion(alphaLevel, 
					calculatingLowerLimit(alphaLevel), 
					calculatingUpperLimit(alphaLevel)));
			
			alphaLevel += 1.0 / countExpansionLeavels;
		}
	}
	
	public static int getCountExpansionLeavels() {
		
		return countExpansionLeavels;
	}
	public static void setCountExpansionLeavels(int countExpansionLeavels) {
		
		FuzzySet.countExpansionLeavels = countExpansionLeavels;
	}
	
	public List<Double> getParameters() {
		
		return parameters;
	}
	public void setParameters(List<Double> parameters) {
		
		this.parameters = parameters;
	}
	
	protected abstract double calculatingLowerLimit(double alphaLevel);
	protected abstract double calculatingUpperLimit(double alphaLevel);
	
	public void draw(Graphics g) {
		
		double maxValue = Double.MIN_VALUE;
		double minVlaue = Double.MAX_VALUE;
		int scale = 250;
		int scaleWeight = 450;
		
		for(int i = 0; i < expansion.size(); i++) {

			double lowerLimit = expansion.get(i).getLowerLimit();
			
			if (lowerLimit != -1) {
				
				if (lowerLimit > maxValue) maxValue = lowerLimit;
				if (lowerLimit < minVlaue) minVlaue = lowerLimit;
			}
			
			double upperLimit = expansion.get(i).getUpperLimit();
			
			if (upperLimit != -1) {
				
				if (upperLimit > maxValue) maxValue = upperLimit;
				if (upperLimit < minVlaue) minVlaue = upperLimit;
			}
		}
		
		double chartWidth = maxValue - minVlaue;
		
		int [] xArrayLower = new int [expansion.size()];
		int [] xArrayUpper = new int [expansion.size()];
		int [] yArray = new int [expansion.size()];
		
		double tmpLowerLimit = 0;
		double tmpUpperLimit = 0;
		
		boolean drawLowerLimit = false;
		boolean drawUpperLimit = false;
		
		for (int i = 0; i < expansion.size(); i++) {
			
			if (expansion.get(i).getLowerLimit() != -1) {
				
				tmpLowerLimit = expansion.get(i).getLowerLimit();
				drawLowerLimit = true;
			}
			
			if (expansion.get(i).getUpperLimit() != -1) {
				
				tmpUpperLimit = expansion.get(i).getUpperLimit();
				drawUpperLimit = true;
			}
				
			xArrayLower[i] = (int)((tmpLowerLimit - minVlaue) / chartWidth * scaleWeight) + 100;
			xArrayUpper[i] = (int)((tmpUpperLimit - minVlaue) / chartWidth * scaleWeight) + 100;
			yArray[i] = (int)((1 - expansion.get(i).getAlphaLevel()) * scale) + 100;
		}
		
		g.setColor(color);
		
		if (drawLowerLimit)
			g.drawPolyline(xArrayLower, yArray, expansion.size()); 
		
		if (drawUpperLimit)
			g.drawPolyline(xArrayUpper, yArray, expansion.size());		
	}	
	
	public String toString() {
		
		return this.getClass().getName() + 
				" (" + parameters.get(0) +
				", " + parameters.get(1) +
				", " + parameters.get(2) +
				", " + parameters.get(3) + ")";
	}
	
	public FuzzySet add(FuzzySet fs) {
		
		List<Expansion> expansions = new ArrayList<Expansion>();
		
		for(int i = 0; i < this.expansion.size() - 1; i++) {
			
			Expansion exp = new Expansion(this.expansion.get(i).getAlphaLevel(),
					this.expansion.get(i).getLowerLimit() + fs.expansion.get(i).getLowerLimit(),
					this.expansion.get(i).getUpperLimit() + fs.expansion.get(i).getUpperLimit());
			
			expansions.add(exp);
		}
		return new OtherFuzzySet(expansions);
	}
	
	public FuzzySet sub(FuzzySet fs) {
		
		List<Expansion> expansions = new ArrayList<Expansion>();
		
		for(int i = 0; i < this.expansion.size() - 1; i++) {
			
			Expansion exp = new Expansion(this.expansion.get(i).getAlphaLevel(),
					this.expansion.get(i).getLowerLimit() - fs.expansion.get(i).getUpperLimit(),
					this.expansion.get(i).getUpperLimit() - fs.expansion.get(i).getLowerLimit());
			
			expansions.add(exp);
		}
		return new OtherFuzzySet(expansions);
	}
	
	public FuzzySet mul(FuzzySet fs) {
		
		List<Expansion> expansions = new ArrayList<Expansion>();
		
		for(int i = 0; i < this.expansion.size() - 1; i++) {
			
			Expansion exp = new Expansion(this.expansion.get(i).getAlphaLevel(),
					this.expansion.get(i).getLowerLimit() * fs.expansion.get(i).getLowerLimit(),
					this.expansion.get(i).getUpperLimit() * fs.expansion.get(i).getUpperLimit());
			
			expansions.add(exp);
		}
		return new OtherFuzzySet(expansions);
	}
	
	public FuzzySet div(FuzzySet fs) {
		
		List<Expansion> expansions = new ArrayList<Expansion>();
		
		for(int i = 0; i < this.expansion.size() - 1; i++) {
			
			Expansion exp = new Expansion(this.expansion.get(i).getAlphaLevel(),
					this.expansion.get(i).getLowerLimit() / fs.expansion.get(i).getUpperLimit(),
					this.expansion.get(i).getUpperLimit() / fs.expansion.get(i).getLowerLimit());
			
			expansions.add(exp);
		}
		return new OtherFuzzySet(expansions);
	}
}
