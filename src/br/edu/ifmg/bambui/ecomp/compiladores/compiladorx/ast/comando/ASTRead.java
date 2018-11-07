package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ASTRead extends ASTComando {

    private final String identificador;

    public ASTRead(String identificador) {
        this.identificador = identificador.toLowerCase();
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        tabelaSimbolo.put(identificador, scanner.nextDouble());

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "scanf(\"%d\", ";

        output += "&"+identificador + ");";
        
        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }
}
