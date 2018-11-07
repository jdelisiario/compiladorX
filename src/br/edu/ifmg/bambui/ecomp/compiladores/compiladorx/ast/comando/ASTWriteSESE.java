package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTWriteSESE extends ASTComando {

    private final ASTExpressao expressao;
    private final ASTExpressao expressao2;
    private final String mensagem;
    private final String mensagem2;

    

    public ASTWriteSESE(String mensagem, ASTExpressao expressao, String mensagem2, ASTExpressao expressao2) {
        this.expressao = expressao;
        this.mensagem = mensagem;
        this.expressao2 = expressao2;
        this.mensagem2 = mensagem2;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        if (expressao != null && mensagem != null && expressao2 != null && mensagem2 != null) {
            expressao.interpretar(tabelaSimbolo);
            System.out.print(mensagem + tabelaSimbolo.get("!"));
            expressao2.interpretar(tabelaSimbolo);
            System.out.println(mensagem2 + tabelaSimbolo.get("!"));
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
        
        String output3 =  "printf(";
        String output4 = "printf(\"%f\",";
        
        output3 += "\"" + mensagem2 + "\"";
       
        if (expressao2 != null) {
            output4 += expressao2.compilar(tabelaSimbolo);
        } 

        output3 += ");";
        output4 += ");";
        
        output += output2;
        output += output3;
        output += output4;
        
        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
