package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTWriteStringExpr extends ASTComando {

    private final ASTExpressao expressao;
    private final String mensagem;

    public ASTWriteStringExpr(String mensagem, ASTExpressao expressao) {
        this.expressao = expressao;
        this.mensagem = mensagem;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        if (expressao != null && mensagem != null) {
            expressao.interpretar(tabelaSimbolo);
            System.out.println(tabelaSimbolo.get("!") + mensagem);
        }
        
        if (expressao != null && mensagem == null) {
            expressao.interpretar(tabelaSimbolo);
            System.out.println(tabelaSimbolo.get("!"));
        }
        if (mensagem != null && expressao == null) {
            System.out.println(mensagem);
        }

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output =  "printf(";
        String output2 = "printf(\"%f\",";
        
        output += "\"" + mensagem + "\"";
       
        if (expressao != null) {
            output2 += expressao.compilar(tabelaSimbolo);
        } 

        output += ");";
        output2 += ");";
        
        output += output2;
        
        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
