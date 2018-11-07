package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import java.util.HashMap;
import java.util.HashSet;

public class ASTWriteString extends ASTComando {

    private final String mensagem;

    public ASTWriteString(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        
        if (mensagem != null) {
            System.out.println(mensagem);
        }

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "printf(";

        
        output += "\"" + mensagem + "\"";
        output += ");";

        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
