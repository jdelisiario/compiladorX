package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTNumero extends ASTExpressao {

	private final Integer valor_inteiro;

	public ASTNumero(Integer valor) {
		super(null, null);
		this.valor_inteiro = valor;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		tabelaSimbolo.put("!", valor_inteiro);
	}
	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return valor_inteiro.toString();
	}
}
