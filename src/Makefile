all:
	jflex Scanner.jflex -d br/edu/ifmg/bambui/ecomp/compiladores/compiladorx
	byaccj -Jclass=ParserCompiladorX -Jpackage=br.edu.ifmg.bambui.ecomp.compiladores.compiladorx -Jthrows=Exception -Jsemantic=ASTNo -Jnoconstruct -Jnorun Parser.y
	mv ParserCompiladorX.java br/edu/ifmg/bambui/ecomp/compiladores/compiladorx