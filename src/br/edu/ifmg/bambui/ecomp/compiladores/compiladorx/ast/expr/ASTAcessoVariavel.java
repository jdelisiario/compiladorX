package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTAcessoVariavel extends ASTExpressao {

    private final String identificador;

    public ASTAcessoVariavel(String identificador) {
        super(null, null);
        this.identificador = identificador.toLowerCase();
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {

        if (!tabelaSimbolo.containsKey(identificador)) {
            throw new Exception("Variável não foi declarada anteriormente.");
        }

        tabelaSimbolo.put("!", tabelaSimbolo.get(identificador));
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        return identificador;
    }

}
