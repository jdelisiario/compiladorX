package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ASTNo;

public abstract class ASTComando extends ASTNo {

	private ASTComando proximo;

	public void setProximo(ASTComando proximo) {
		this.proximo = proximo;
	}

	public ASTComando getProximo() {
		return proximo;
	}
}
