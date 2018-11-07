package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTDivisao extends ASTExpressao {

	public ASTDivisao(ASTExpressao esquerda, ASTExpressao direita) {
		super(esquerda, direita);
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
            double t1,t2;
            
		getEsquerda().interpretar(tabelaSimbolo);
		t1 = (Double) tabelaSimbolo.get("!");
		getDireita().interpretar(tabelaSimbolo);
		t2 = (Double) tabelaSimbolo.get("!");
		tabelaSimbolo.put("!", t1/t2);
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {           
            if(Double.parseDouble(getDireita().compilar(tabelaSimbolo)) == 0){
                throw new RuntimeException("Nao eh possivel divisao por 0");
            }
            return "("+getEsquerda().compilar(tabelaSimbolo)+"/"+getDireita().compilar(tabelaSimbolo)+")";
	}

	
}
