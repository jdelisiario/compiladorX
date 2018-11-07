package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTNot extends ASTExpressao {
    
	public ASTNot(ASTExpressao direita) {
		super(null, direita);
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		boolean t1;
		
		getDireita().interpretar(tabelaSimbolo);
		t1 = (boolean) tabelaSimbolo.get("!");
		tabelaSimbolo.put("!", !t1);
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return "("+"!"+getDireita().compilar(tabelaSimbolo)+")";
	}
}
