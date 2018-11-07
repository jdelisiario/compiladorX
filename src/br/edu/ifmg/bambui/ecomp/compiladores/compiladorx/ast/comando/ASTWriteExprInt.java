package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTWriteExprInt extends ASTComando {

    private final ASTExpressao expressao;

    public ASTWriteExprInt(ASTExpressao expressao) {
        this.expressao = expressao;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        if (expressao != null) {
            expressao.interpretar(tabelaSimbolo);
            System.out.println(tabelaSimbolo.get("!"));
        }


        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "printf";   
        
        if (expressao != null) {
                output += "(\"%d\",";
                output += expressao.compilar(tabelaSimbolo);
         
        }

        output += ");";

        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }
        System.out.println(output);
        return output;
    }

}
