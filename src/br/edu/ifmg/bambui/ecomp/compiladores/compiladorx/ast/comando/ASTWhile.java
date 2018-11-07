package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTWhile extends ASTComando {
	
	private final ASTExpressao condicao;
	private final ASTComando blocoDo;

	public ASTWhile(ASTExpressao condicao, ASTComando blocoDo) {
		this.condicao = condicao;
		this.blocoDo = blocoDo;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		condicao.interpretar(tabelaSimbolo);
		
		if(!(tabelaSimbolo.get("!") instanceof Boolean)) {
			throw new Exception("A expressão em condicionais deve ser um booleano.");
		}
		
                while((Boolean)tabelaSimbolo.get("!")){
                    blocoDo.interpretar(tabelaSimbolo);
                }
                
		
		if(getProximo()!=null) {
			getProximo().interpretar(tabelaSimbolo);
		}
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		String output = "while("+condicao.compilar(tabelaSimbolo)+"){"
                                +blocoDo.compilar(tabelaSimbolo)+"}";
		
		
		
		if(getProximo() != null) {
			output+=getProximo().compilar(tabelaSimbolo);
		}
		
		return output;
	}
}
