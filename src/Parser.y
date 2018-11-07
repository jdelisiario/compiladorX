/* CONFIGURAÇÃO DO PARSER */

%token TK_WRITE TK_READ 
%token TK_IF TK_FI TK_END TK_THEN TK_ELSE TK_FALSE TK_TRUE
%token IDENTIFICADOR NUMERO STRING PONTO_FLUTUANTE TK_ATRIBUICAO 
%token TK_INT TK_REAL TK_BOOL TK_DECL
%token TK_FOR TK_FROM TK_TO TK_DO TK_DONE TK_WHILE
%token TK_DIFERENTE TK_MAIORIGUAL TK_MENORIGUAL
%token TK_AND TK_OR TK_NOT

/* PRECEDENCIA */
%left '<' '>' TK_MAIORIGUAL TK_MENORIGUAL '=' TK_DIFERENTE
%left TK_AND TK_NOT TK_OR
%left '+' '-'
%left '*' '/'

%{
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.compiladorx.ast.expr.*;
%}

%%
/* REGRAS GRAMATICAIS */

main : lista_comandos { raiz = $1; }

lista_comandos :
    comando ';' { $$ = $1; }
|   lista_comandos comando ';' { $$ = $1; buscarUltimoComando((ASTComando)$$).setProximo((ASTComando)$2); }
;

comando :
|   TK_WRITE STRING { $$ = new ASTWriteString(((Token)$2).getLexema()); }
|   TK_READ IDENTIFICADOR { $$ = new ASTRead(((Token)$2).getLexema()); }  
|   TK_IF exprCondicao TK_THEN lista_comandos TK_FI { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4); }
|   TK_IF exprCondicao TK_THEN lista_comandos TK_ELSE lista_comandos TK_FI { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4,(ASTComando)$6); }
|   TK_FOR expr TK_FROM expr TK_TO expr TK_DO lista_comandos TK_DONE { $$ = new ASTFor((ASTExpressao)$2,(ASTExpressao)$4,(ASTExpressao)$6,(ASTComando)$8); }
|   TK_WHILE exprCondicao TK_DO lista_comandos TK_DONE {  $$ = new ASTWhile((ASTExpressao)$2,(ASTComando)$4);  }

|   IDENTIFICADOR TK_ATRIBUICAO expr { $$ = new ASTAtribuicao(((Token)$1).getLexema(),(ASTExpressao)$3); }

|   IDENTIFICADOR '[' expr ']' TK_ATRIBUICAO expr  { $$ = new ASTAtribuicaoVetId(((Token)$1).getLexema(),(ASTExpressao)$6,(ASTExpressao)$3); }
|   IDENTIFICADOR TK_ATRIBUICAO IDENTIFICADOR '[' expr ']' { $$ = new ASTAtribuicaoIdVet(((Token)$1).getLexema(),((Token)$3).getLexema(),(ASTExpressao)$5); }
|   IDENTIFICADOR '[' expr ']' TK_ATRIBUICAO IDENTIFICADOR '[' expr ']' { $$ = new ASTAtribuicaoVetVet(((Token)$1).getLexema(),(ASTExpressao)$3,((Token)$6).getLexema(),(ASTExpressao)$8); }

//  CODIGO DA PARTE INT
|   TK_WRITE expr { $$ = new ASTWriteExprInt((ASTExpressao)$2); }
|   TK_WRITE STRING ',' exprInt { $$ = new ASTWriteStringExprInt(((Token)$2).getLexema(),(ASTExpressao)$4); }
|   TK_WRITE expr ',' STRING { $$ = new ASTWriteExprStringInt(((Token)$4).getLexema(),(ASTExpressao)$2); }
|   TK_WRITE STRING ',' expr ',' STRING ',' expr { $$ = new ASTWriteSESEInt(((Token)$2).getLexema(),(ASTExpressao)$4, ((Token)$6).getLexema(),(ASTExpressao)$8); }
|   TK_DECL expr ':' TK_INT { $$ = new ASTDeclInt((ASTExpressao)$2); }
|   TK_DECL expr ':' TK_INT '[' exprVetor ']' { $$ = new ASTDeclIntVetor((ASTExpressao)$2,(ASTExpressao)$6); }

//  CODIGO DA PARTE REAL
|   TK_WRITE expr { $$ = new ASTWriteExpr((ASTExpressao)$2); }
|   TK_WRITE STRING ',' expr { $$ = new ASTWriteStringExpr(((Token)$2).getLexema(),(ASTExpressao)$4); }
|   TK_WRITE expr ',' STRING { $$ = new ASTWriteExprString(((Token)$4).getLexema(),(ASTExpressao)$2); }
|   TK_WRITE STRING ',' expr ',' STRING ',' expr { $$ = new ASTWriteSESE(((Token)$2).getLexema(),(ASTExpressao)$4, ((Token)$6).getLexema(),(ASTExpressao)$8); }
|   TK_DECL expr ':' TK_REAL { $$ = new ASTDeclReal((ASTExpressao)$2); }
|   TK_DECL expr ':' TK_REAL '[' exprVetor ']' { $$ = new ASTDeclRealVetor((ASTExpressao)$2,(ASTExpressao)$6); }


//  CODIGO DA PARTE BOOL
|   TK_DECL expr ':' TK_BOOL { $$ = new ASTDeclBool((ASTExpressao)$2); }
|   IDENTIFICADOR TK_ATRIBUICAO TK_TRUE { $$ = new ASTAtribuicaoTrue(((Token)$1).getLexema()); }
|   IDENTIFICADOR TK_ATRIBUICAO TK_FALSE { $$ = new ASTAtribuicaoFalse(((Token)$1).getLexema()); }

;

exprBinario :
|   IDENTIFICADOR { $$ = new ASTAcessoVariavel(((Token)$1).getLexema()); }
|   NUMERO { $$ = new ASTNumero(new Integer(((Token)$1).getLexema())); }
|   PONTO_FLUTUANTE { $$ = new ASTPontoFlutuante(new Double(((Token)$1).getLexema())); }
|   IDENTIFICADOR '[' expr ']' { $$ = new ASTVetor((ASTExpressao)$3,((Token)$1).getLexema()); }
;

exprVetor :
|   IDENTIFICADOR { $$ = new ASTAcessoVariavel(((Token)$1).getLexema()); }
|   NUMERO { $$ = new ASTNumero(new Integer(((Token)$1).getLexema())); }
;

exprCondicao :
|   exprCondicao TK_AND exprCondicao { $$ = new ASTAnd((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprCondicao TK_OR exprCondicao { $$ = new ASTOr((ASTExpressao)$1,(ASTExpressao)$3); }
|   TK_NOT exprBinario { $$ = new ASTNot((ASTExpressao)$2); }
|   exprBinario '<' exprBinario { $$ = new ASTMenor((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario '>' exprBinario { $$ = new ASTMaior((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_MAIORIGUAL exprBinario { $$ = new ASTMaiorIgual((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_MENORIGUAL exprBinario { $$ = new ASTMenorIgual((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario '=' exprBinario { $$ = new ASTCompara((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_DIFERENTE exprBinario { $$ = new ASTDiferente((ASTExpressao)$1,(ASTExpressao)$3); }
;

exprInt: 
|   exprInt ',' exprInt { $$ = new ASTSeparacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprInt '+' exprInt { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprInt '-' exprInt { $$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprInt '*' exprInt { $$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprInt '/' exprInt { $$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '=' expr { $$ = new ASTCompara((ASTExpressao)$1,(ASTExpressao)$3);}
|   NUMERO { $$ = new ASTNumero(new Integer(((Token)$1).getLexema())); }
;

exprFloat: 
|   exprFloat ',' exprFloat { $$ = new ASTSeparacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprFloat '+' exprFloat { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprFloat '-' exprFloat { $$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprFloat '*' exprFloat { $$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprFloat '/' exprFloat { $$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '=' expr { $$ = new ASTCompara((ASTExpressao)$1,(ASTExpressao)$3);}
|   PONTO_FLUTUANTE { $$ = new ASTPontoFlutuante(new Double(((Token)$1).getLexema())); }
;

exprBool:

;

expr :
|   IDENTIFICADOR { $$ = new ASTAcessoVariavel(((Token)$1).getLexema()); }
//|   PONTO_FLUTUANTE { $$ = new ASTPontoFlutuante(new Double(((Token)$1).getLexema())); }
|   expr ',' expr { $$ = new ASTSeparacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '+' expr { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '-' expr { $$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '*' expr { $$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '/' expr { $$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); }
|	'(' expr ')' { $$ = $2; }
|   IDENTIFICADOR '(' ')'{ $$ = new ASTFuncao(((Token)$1).getLexema()); }
|   IDENTIFICADOR '[' expr ']' { $$ = new ASTVetor((ASTExpressao)$3,((Token)$1).getLexema()); }
|   expr '=' expr { $$ = new ASTCompara((ASTExpressao)$1,(ASTExpressao)$3);}
//|   NUMERO { $$ = new ASTNumero(new Integer(((Token)$1).getLexema())); }	
|   exprInt { $$ = $1; }
|   exprFloat { $$ = $1; }

//|   TK_FALSE { $$ = new ASTBooleano(new Boolean(((Token)$1).getLexema())); }
//|   TK_TRUE  { $$ = new ASTBooleano(new Boolean(((Token)$1).getLexema())); }
|   exprBinario TK_AND exprBinario { $$ = new ASTAnd((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_OR exprBinario { $$ = new ASTOr((ASTExpressao)$1,(ASTExpressao)$3); }
|   TK_NOT exprBinario { $$ = new ASTNot((ASTExpressao)$2); }
|   exprBinario '<' exprBinario { $$ = new ASTMenor((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario '>' exprBinario { $$ = new ASTMaior((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_MAIORIGUAL exprBinario { $$ = new ASTMaiorIgual((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_MENORIGUAL exprBinario { $$ = new ASTMenorIgual((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario '=' exprBinario { $$ = new ASTCompara((ASTExpressao)$1,(ASTExpressao)$3); }
|   exprBinario TK_DIFERENTE exprBinario { $$ = new ASTDiferente((ASTExpressao)$1,(ASTExpressao)$3); }
;

%%
/* PARTE INTERNA DA CLASSE */

private ASTNo raiz;

private ScannerCompiladorX scanner;

public ParserCompiladorX(Reader reader) {
    scanner = new ScannerCompiladorX(reader,this);
}

private int yylex() throws IOException {
    return scanner.yylex();
}

private void yyerror(String msg) throws Exception {
    throw new Exception(msg);
}

private ASTComando buscarUltimoComando(ASTComando cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}

	return cmd;
}

public void interpretar() throws Exception {
    yydebug=true;
	yyparse();
	raiz.interpretar(new HashMap<String,Object>());
}

public void compilar() throws Exception {
	HashSet<String> tabelaSimbolo = new HashSet<String>();
	PrintWriter printWriter;
    String output;
	String saida;

	yyparse();
	saida = raiz.compilar(tabelaSimbolo);
	
	output  = "#include <stdio.h> " + "\n";
	output += "#include <stdlib.h> " + "\n";
	
        output += "enum boolean{true = 1, false = 0};typedef  enum boolean  bool;\n";
        
        output += "int main() { " + "\n";
	

	for(int i=0;i<tabelaSimbolo.size();i++) {
		output += tabelaSimbolo.toArray()[i];

                System.out.println("Posicao " + i + ":" + tabelaSimbolo.toArray()[i]);
		if(i < tabelaSimbolo.size()-1) {
                        
			output += ",";
		}
	}

        
        output += saida;
        //output += ";";
	output += "\n" + "return 0;";
        output += "\n";
        output += "}";

	printWriter = new PrintWriter("/tmp/cOutput.c","UTF-8");
	printWriter.print(output);
	printWriter.close();
}