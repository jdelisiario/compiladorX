/* Regras do analisador léxico do CompiladorX */

package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx;

import java.io.Reader;

%%

%class ScannerCompiladorX
%public
%ignorecase
%line
%byaccj

%{

private ParserCompiladorX parser;

public ScannerCompiladorX(Reader reader, ParserCompiladorX parser) {
    this(reader);
    this.parser = parser;
}

%}

%%

\{[^\}]*\}+ { System.out.println("Comentario reconhecido(nao retorna): " +yytext()); }

[0-9]+ { System.out.println("Numero inteiro: " + yytext());
    parser.yylval = new Token(yyline,yytext());
    return ParserCompiladorX.NUMERO;
}

[0-9]+(\.[0-9]+)?(E[+-]?[0-9])? {
    System.out.println("Ponto Flutuante: " + yytext());
    parser.yylval = new Token(yyline,yytext());
    return ParserCompiladorX.PONTO_FLUTUANTE;
}

"int" { System.out.println("Palavra Reservada: " + yytext());
          return ParserCompiladorX.TK_INT; }
"real" { System.out.println("Palavra Reservada: " + yytext());
          return ParserCompiladorX.TK_REAL; }
"bool" { System.out.println("Palavra Reservada: " + yytext());
          return ParserCompiladorX.TK_BOOL; }
"write" { System.out.println("Palavra Reservada: " + yytext());
          return ParserCompiladorX.TK_WRITE; }
"read" { System.out.println("Palavra Reservada: " + yytext());
         return ParserCompiladorX.TK_READ; }
"if" { System.out.println("Palavra Reservada: " + yytext());
       return ParserCompiladorX.TK_IF; }
"fi" { System.out.println("Palavra Reservada: " + yytext());
       return ParserCompiladorX.TK_FI; }
"then" { System.out.println("Palavra Reservada: " + yytext());
         return ParserCompiladorX.TK_THEN; }
"else" { System.out.println("Palavra Reservada: " + yytext()); 
        return ParserCompiladorX.TK_ELSE; }
"for" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_FOR; }
"from" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_FROM; }
"to" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_TO; }
"do" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_DO; }
"done" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_DONE; }
"while" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_WHILE; }
"decl" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_DECL; }


"<-" { System.out.println("Atribuicao: " + yytext());
        return ParserCompiladorX.TK_ATRIBUICAO; }
"false" { System.out.println("Palavra Reservada: " + yytext()); 
         return ParserCompiladorX.TK_FALSE; }
"true" { System.out.println("Palavra Reservada: " + yytext());
        return ParserCompiladorX.TK_TRUE; }

">=" { System.out.println("Operador: " + yytext());
        return ParserCompiladorX.TK_MAIORIGUAL; }
"<=" { System.out.println("Operador: " + yytext());
        return ParserCompiladorX.TK_MENORIGUAL; }
"<>" { System.out.println("Operador: " + yytext());
        return ParserCompiladorX.TK_DIFERENTE; }

"and" { System.out.println("Operador: " + yytext());
        return ParserCompiladorX.TK_AND; }
"not" { System.out.println("Operador: " + yytext());
        return ParserCompiladorX.TK_NOT; }
"or" { System.out.println("Operador: " + yytext());
        return ParserCompiladorX.TK_OR; }


[a-zA-Z$_][a-zA-Z0-9$_]* {
    System.out.println("Identificador: " + yytext());
    parser.yylval = new Token(yyline,yytext());
    return ParserCompiladorX.IDENTIFICADOR;
}

\" [^\"]* \" {
    System.out.println("String: " + yytext());
    parser.yylval = new Token(yyline, yytext().substring(1,yytext().length()-1));
    return ParserCompiladorX.STRING;
}

[ \t\n]+ { /* IGNORE */ }

. { return yytext().charAt(0); }