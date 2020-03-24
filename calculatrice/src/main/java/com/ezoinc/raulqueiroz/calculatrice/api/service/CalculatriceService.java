package com.ezoinc.raulqueiroz.calculatrice.api.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.boot.archive.spi.ArchiveException;
import org.springframework.stereotype.Service;

import com.ezoinc.raulqueiroz.calculatrice.api.enumeration.Operandes;
import com.ezoinc.raulqueiroz.calculatrice.api.mathematique.ExpressionMathematique;
import com.ezoinc.raulqueiroz.calculatrice.api.mathematique.Noeud;

@Service
public class CalculatriceService {

	public Double calculer(String chaine) {
		if (chaine == null)
			return null;

		String chaineInitiale = new String(chaine);
		String nouvelleExpression = new String(this.transformerExpression(chaine));
		return this.resoudre(nouvelleExpression, new ExpressionMathematique(nouvelleExpression), chaineInitiale);
	}

	public Double resoudre(String chaine, ExpressionMathematique expressionMathematique, String chaineInitiale) {

		try {
			Noeud noeud = this.visiteurNoeud(chaine, expressionMathematique, chaineInitiale);
			return evaluate(noeud);
		} catch (ArithmeticException e) {
			throw new ArchiveException("Erreur: " + e.getMessage());
		}
	}

	private static Double evaluate(Noeud noeudActuel) {
		if (noeudActuel.aOperator() && noeudActuel.avoirLesFils()) {
			if (noeudActuel.getOperator().getType() == Operandes.UNAIRE)

				noeudActuel.setValeur(noeudActuel.getOperator().resoudre(evaluate(noeudActuel.getGauche()), null));
			else if (noeudActuel.getOperator().getType() == Operandes.BINAIRE)
				noeudActuel.setValeur(noeudActuel.getOperator().resoudre(evaluate(noeudActuel.getGauche()),
						evaluate(noeudActuel.getDroit())));
		}
		return noeudActuel.getValue();
	}

	private Noeud visiteurNoeud(String chaine, ExpressionMathematique expressionMathematique, String chaineInitiale) {
		return new Noeud(chaine, expressionMathematique, chaineInitiale);

	}

	public String transformerExpression(String chaine) {
		chaine = chaine.replace("[", "(").replace("]", ")").replace("{", "(").replace("}", ")").replace(" ", "")
				.replaceAll("[+]{1,}", "+");
		chaine = this.verifierOperationsSoustractionRepetees(chaine);

		return chaine;
	}

	private String verifierOperationsSoustractionRepetees(String chaine) {

		Pattern pattern = Pattern.compile("[-]{1}");
		Matcher matcher = pattern.matcher(chaine);

		int compteur = 0;

		while (matcher.find()) {
			compteur++;
		}

		if (compteur != 0 && compteur % 2 == 0) {
			return chaine.replaceAll("[-]{1,}", "+");
		}

		if (compteur != 0 && compteur % 2 != 0) {
			return chaine.replaceAll("[-]{1,}", "-");
		}

		return chaine;
	}
}
