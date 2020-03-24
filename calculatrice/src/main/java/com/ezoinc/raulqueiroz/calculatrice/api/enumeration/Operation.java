package com.ezoinc.raulqueiroz.calculatrice.api.enumeration;

import com.ezoinc.raulqueiroz.calculatrice.api.interfaces.Resoudre;

/**
 * @author Raul Queiroz
 */
public enum Operation implements Resoudre {

	ADDITION("+", Operandes.BINAIRE, 0) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return f1 + f2;
		}
	},

	SOUSTRACTION("-", Operandes.BINAIRE, 0) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return f1 - f2;
		}
	},

	MULTIPLICATION("*", Operandes.BINAIRE, 1) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return f1 * f2;
		}
	},
	
	DIVISION("/", Operandes.BINAIRE, 1) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return f1 / f2;
		}
	},

	POW("^", Operandes.BINAIRE, 1) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.pow(f1, f2);
		}
	},
	
	SQRT("sqrt", Operandes.UNAIRE, 2) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.sqrt(f1);
		}
	},
	
	MOD("%", Operandes.BINAIRE, 1) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return f1 % f2;
		}

	},
	
	SIN("sin", Operandes.UNAIRE, 3) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.sin(f1);
		}
	},
	
	COS("cos", Operandes.UNAIRE, 3) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.cos(f1);
		}
	},
	
	TAN("tan", Operandes.UNAIRE, 3) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.tan(f1);
		}
	},
	
	LOG("log", Operandes.UNAIRE, 3) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.log(f1);
		}
	},
	
	LOG10("log10", Operandes.UNAIRE, 3) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.log10(f1);
		}
	},
	
	CBRT("cbrt", Operandes.UNAIRE, 3) {
		@Override
		public Double resoudre(Double f1, Double f2) {
			return Math.cbrt(f1);
		}
	};

	
	private String operator;
	private Operandes type;
	private int priorite;

	private Operation(String operator, Operandes type, int priorite) {
		this.operator = operator;
		this.type = type;
		this.priorite = priorite;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Operandes getType() {
		return type;
	}

	public void setType(Operandes type) {
		this.type = type;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

}