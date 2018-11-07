package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTPontoFlutuante extends ASTExpressao {

	private final Double valor;

	public ASTPontoFlutuante(Double valor) {
		super(null, null);
		this.valor = valor;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		tabelaSimbolo.put("!", valor);
	}
	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return valor.toString();
	}
}
