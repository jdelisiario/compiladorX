package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTVetor extends ASTExpressao {

        private final String identificador;
        private final ASTExpressao expressao;
        
	public ASTVetor(ASTExpressao expressao, String identificador) {
		super(null, null);
		this.identificador = identificador.toLowerCase();
                this.expressao = expressao;
        }

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
            tabelaSimbolo.put("!", tabelaSimbolo.get(identificador));
            tabelaSimbolo.put("!", expressao);
	}
	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
            return identificador.toString() + '[' + expressao.compilar(tabelaSimbolo) + ']';
        }
}
