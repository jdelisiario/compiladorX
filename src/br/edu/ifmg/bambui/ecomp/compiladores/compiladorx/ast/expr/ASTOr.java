package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTOr extends ASTExpressao {

	public ASTOr(ASTExpressao esquerda, ASTExpressao direita) {
		super(esquerda, direita);
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		boolean t1,t2;
		
		getEsquerda().interpretar(tabelaSimbolo);
		t1 = (boolean) tabelaSimbolo.get("!");
		getDireita().interpretar(tabelaSimbolo);
		t2 = (boolean) tabelaSimbolo.get("!");
		tabelaSimbolo.put("!", t1 || t2);
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return "("+getEsquerda().compilar(tabelaSimbolo)+"||"+getDireita().compilar(tabelaSimbolo)+")";
	}
}
