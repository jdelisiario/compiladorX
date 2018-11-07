package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx;

import java.util.HashMap;
import java.util.HashSet;
 
public class ASTNo {

	public ASTNo() {
		
	}
 
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		throw new RuntimeException("Não é possível utilizar este método da classe ASTNo, é necessário sobreescrevê-lo");
	}
	
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		throw new RuntimeException("Não é possível utilizaro método da classe ASTNo, é necessário sobreescrever");
	}
}