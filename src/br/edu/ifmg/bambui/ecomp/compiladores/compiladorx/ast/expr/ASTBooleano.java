package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTBooleano extends ASTExpressao {

	private final Boolean valor_booleano;

	public ASTBooleano(Boolean valor) {
		super(null, null);
		this.valor_booleano = valor;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		tabelaSimbolo.put("!", valor_booleano);
	}
	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return null;
            //return valor_booleano.toString();
	}
}
