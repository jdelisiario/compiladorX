package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

public class ASTFor extends ASTComando {

    private final ASTExpressao contador;
    private final ASTExpressao inicio;
    private final ASTExpressao fim;
    private final ASTComando blocoDo;

    public ASTFor(ASTExpressao contador, ASTExpressao inicio, ASTExpressao fim, ASTComando blocoDo) {
        this.contador = contador;
        this.inicio = inicio;
        this.fim = fim;
        this.blocoDo = blocoDo;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        contador.interpretar(tabelaSimbolo);

        int inicio_interpretar, fim_interpretar, contador_interpretar;

        getInicio().interpretar(tabelaSimbolo);
        inicio_interpretar = (Integer) tabelaSimbolo.get("!");
        getFim().interpretar(tabelaSimbolo);
        fim_interpretar = (Integer) tabelaSimbolo.get("!");
        getContador().interpretar(tabelaSimbolo);
        contador_interpretar = (Integer) tabelaSimbolo.get("!");

        for (int i = inicio_interpretar; i < fim_interpretar; contador_interpretar++) {
            blocoDo.interpretar(tabelaSimbolo);
            if (getProximo() != null) {
                getProximo().interpretar(tabelaSimbolo);
            }
        }
        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    public ASTExpressao getContador() {
        return contador;
    }

    public ASTExpressao getInicio() {
        return inicio;
    }

    public ASTExpressao getFim() {
        return fim;
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "for("+contador.compilar(tabelaSimbolo)+ "=" + inicio.compilar(tabelaSimbolo) + ";";
        output += contador.compilar(tabelaSimbolo) + "<=" + fim.compilar(tabelaSimbolo) + ";";
	output += contador.compilar(tabelaSimbolo) + "++)";
        
        
         if(blocoDo != null) {
         output+="{"+blocoDo.compilar(tabelaSimbolo)+"}";
         }
		
         if(getProximo() != null) {
         output+=getProximo().compilar(tabelaSimbolo);
         }
		
         return output;
    }
}
