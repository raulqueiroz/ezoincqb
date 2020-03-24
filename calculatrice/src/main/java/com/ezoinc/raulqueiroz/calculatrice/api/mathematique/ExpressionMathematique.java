package com.ezoinc.raulqueiroz.calculatrice.api.mathematique;

import java.util.HashMap;
import java.util.Map;

public class ExpressionMathematique {

	private String expression;
	private Map<String, Double> variables = new HashMap<String, Double>();

	public ExpressionMathematique(String chaine) {
		this.expression = chaine;

	}

	public void setExpression(String str) {
		expression = str;
	}

	public String getExpression() {
		return this.expression;
	}

	public Double getDouble(String str) {
		try {
			return new Double(Double.parseDouble(str));
		} catch (Exception e) {
			return getVariable(str);
		}
	}

	public void setVariable(String valeur, double val) {
		variables.put(valeur, new Double(val));
	}

	public Double getVariable(String str) {
		return variables.get(str);
	}

}