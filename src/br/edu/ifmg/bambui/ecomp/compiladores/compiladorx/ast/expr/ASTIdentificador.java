package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTIdentificador extends ASTExpressao {

	private final String variavel;

	public ASTIdentificador(String valor) {
		super(null, null);
		this.variavel = valor.toLowerCase();
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		tabelaSimbolo.put("!", variavel);
	}
	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return "float " + variavel.toString();
	}
}
