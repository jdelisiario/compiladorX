package br.edu.ifmg.bambui.ecomp.compiladores.compiladorx;

import java.io.FileReader;
import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) throws Exception {
		JFileChooser chooser = new JFileChooser();
		ParserCompiladorX parser;
		
		System.out.println("--------------- COMPILADOR X ---------------");
		System.out.println("--- ESCOLHA O ARQUIVO COM O CODIGO FONTE ---");
		System.out.println("--------------------------------------------\n\n");
		
		chooser.showOpenDialog(null);
		parser = new ParserCompiladorX(new FileReader(chooser.getSelectedFile()));
		
		parser.compilar();
	}
}