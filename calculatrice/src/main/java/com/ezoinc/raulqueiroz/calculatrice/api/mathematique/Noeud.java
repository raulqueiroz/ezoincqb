package com.ezoinc.raulqueiroz.calculatrice.api.mathematique;

import com.ezoinc.raulqueiroz.calculatrice.api.enumeration.Operandes;
import com.ezoinc.raulqueiroz.calculatrice.api.enumeration.Operation;

public class Noeud {
	private Operation operator = null;
	private Noeud gauche = null;
	private Noeud droit = null;
	private Double valeur = null;

	public Noeud(ExpressionMathematique expression) {
		this(expression.getExpression(), expression, null);
	}

	public Noeud(String chaine, ExpressionMathematique expressionMathematique,  String chaineInitiale) {
		chaine = analyserDebutExpression(chaine);
		chaine = supprimerParentheses(chaine);
		
		if (!verifierParentheses(chaine))
			throw new IllegalArgumentException(
					"\r\n" + "Quantité de les 'parenthèses, crochets ou accolades incorrecte dans l'expression: " + chaineInitiale + "\n");
		

		valeur = expressionMathematique.getDouble(chaine);
		int inParentheses = 0;
		int demarrerOperateur = 0;

		for (int i = 0; i < chaine.length(); i++) {
			if (chaine.charAt(i) == '(')
				inParentheses++;
			else if (chaine.charAt(i) == ')')
				inParentheses--;
			else {
				if (inParentheses == 0) {
					Operation operation = getOperator(chaine, i);
					if (operation != null)
						if (operator == null || operator.getPriorite() >= operation.getPriorite()) {
							operator = operation;
							demarrerOperateur = i;
						}
				}
			}
		}

		if (operator != null) {
			if (demarrerOperateur == 0 && operator.getType() == Operandes.UNAIRE) {
				if (verifierParentheses(chaine.substring(operator.getOperator().length()))) {
					gauche = new Noeud(chaine.substring(operator.getOperator().length()), expressionMathematique, null);
					return;
				}				
			} else if (demarrerOperateur > 0 && operator.getType() == Operandes.BINAIRE) {
				gauche = new Noeud(chaine.substring(0, demarrerOperateur), expressionMathematique, null);
				droit = new Noeud(chaine.substring(demarrerOperateur + operator.getOperator().length()), expressionMathematique, null);
			}
		}
	}
	
	public String transformerExpression(String chaine) {
		return chaine.replace("[", "(").replace("]", ")").replace("{", "(").replace("}", ")");
	}

	public Operation getOperator(String str, int start) {
		Operation[] operators = Operation.values();
		String next = getProchain(str.substring(start));
		
		for (Operation operation : operators) {
			if (next.startsWith(operation.getOperator()))
				return operation;
		} 
		return null;
	}

	public String getProchain(String str) {
		for (int i = 1; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c > 'z' || c < 'a') && (c > '9' || c < '0')) {
				return str.substring(0, i);
			}
		}
		return str;
	}

	public boolean verifierParentheses(String chaine) {
		int parentheses = 0;
		for (int i = 0; i < chaine.length(); i++) {
			if (chaine.charAt(i) == '(' && parentheses >= 0)
				parentheses++;
			else if (chaine.charAt(i) == ')')
				parentheses--;
		}
		return parentheses == 0;

	}

	private String supprimerParentheses(String chaine) {
		String str = chaine;
		if (chaine.length() > 2 && str.startsWith("(") && str.endsWith(")")
				&& verifierParentheses(chaine.substring(1, chaine.length() - 1)))
			str = str.substring(1, str.length() - 1);

		if (str != chaine)
			return supprimerParentheses(str);
		return str;
	}

	public String analyserDebutExpression(String str) {
		if (str.startsWith("+") || str.startsWith("-"))
			return "0" + str;
		if (str.startsWith("*") || str.startsWith("/") || str.startsWith("'^'") || str.startsWith("%")) 
			return str.substring(1);

		return str;
	}

	public boolean avoirLesFils() {
		return gauche != null || droit != null;
	}

	public boolean aOperator() {
		return operator != null;
	}

	public boolean aGauche() {
		return gauche != null;
	}

	public Noeud getGauche() {
		return gauche;
	}

	public boolean aDroit() {
		return droit != null;
	}

	public Noeud getDroit() {
		return droit;
	}

	public Operation getOperator() {
		return operator;
	}

	public Double getValue() {
		return this.valeur;
	}

	public void setValeur(Double value) {
		this.valeur = value;
	}
}