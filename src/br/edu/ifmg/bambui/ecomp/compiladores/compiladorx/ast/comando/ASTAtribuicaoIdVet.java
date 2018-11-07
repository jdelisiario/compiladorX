package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTAtribuicaoIdVet extends ASTComando {

    private final String identificador;
    private final String identificador2;
    private final ASTExpressao expressao;

    public ASTAtribuicaoIdVet(String identificador, String identificador2, ASTExpressao expressao) {
        this.identificador = identificador.toLowerCase();
        this.identificador2 = identificador2.toLowerCase();
        this.expressao = expressao;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        expressao.interpretar(tabelaSimbolo);

        if (!(tabelaSimbolo.get("!") instanceof Double) && !(tabelaSimbolo.get("!") instanceof Integer)
                && !(tabelaSimbolo.get("!") instanceof Boolean)) {
            throw new Exception("Atribuições só podem ser feitas por números, pontos"
                    + "         flutuantes e booleanos");
        }

        tabelaSimbolo.put(identificador, tabelaSimbolo.get("!"));

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "";

        output = identificador.toLowerCase() + " = " + identificador2.toLowerCase() + "[" + expressao.compilar(tabelaSimbolo) + "];"; 
        
        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
