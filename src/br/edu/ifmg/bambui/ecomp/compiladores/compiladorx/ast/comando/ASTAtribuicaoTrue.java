package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTAtribuicaoTrue extends ASTComando {

    private final String identificador;

    public ASTAtribuicaoTrue(String identificador) {
        this.identificador = identificador.toLowerCase();
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        
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

        output = identificador + " = true;";

        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
