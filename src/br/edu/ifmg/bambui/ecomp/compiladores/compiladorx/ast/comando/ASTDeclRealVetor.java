package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTDeclRealVetor extends ASTComando {

    private final ASTExpressao expressao;
    private final ASTExpressao expressao2;

    public ASTDeclRealVetor(ASTExpressao expressao, ASTExpressao expressao2) {
        this.expressao = expressao;
        this.expressao2 = expressao2;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        
        if (expressao != null) {
            expressao.interpretar(tabelaSimbolo);
            System.out.println(tabelaSimbolo.get("!"));
        }
        
        if (expressao2 != null) {
            expressao2.interpretar(tabelaSimbolo);
            System.out.println(tabelaSimbolo.get("!"));
        }

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "";
        
        output += "float " + expressao.compilar(tabelaSimbolo);
        output += "[" + expressao2.compilar(tabelaSimbolo) + "]";
        output+= ";";
        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }
        
        
        return output;       
    }

}
