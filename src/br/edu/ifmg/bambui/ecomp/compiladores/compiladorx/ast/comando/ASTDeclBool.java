package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;


public class ASTDeclBool extends ASTComando {

    private final ASTExpressao expressao;

    public ASTDeclBool(ASTExpressao expressao) {
        this.expressao = expressao;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        
        
        if (expressao != null) {
            expressao.interpretar(tabelaSimbolo);
        }

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "";
        
        output += "bool " + expressao.compilar(tabelaSimbolo);
        output+= ";";
        if (getProximo() != null) {  
            output += getProximo().compilar(tabelaSimbolo);     
        }
        
        
        return output;
    }

}