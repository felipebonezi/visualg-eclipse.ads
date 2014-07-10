package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;
import org.eclipse.swt.graphics.Color;

public class VisualGScanner extends RuleBasedScanner {
	
	//Lista de todas as palavras reservadas
	private static final String[] VG_RESERVED_WORDS = {
		"aleatório", "enquanto", "funcao", "para", 
		"algoritmo", "entao", "inicio", "passo", 
		"arquivo", "escolha", "int", "pausa", 
		"asc", "escreva", "inteiro", "pos", 
		"ate", "escreval", "interrompa", "real", 
		"carac", "faca", "leia", "procedimento", 
		"caracpnum", "falso", "limpatela", "repita", 
		"caractere", "fimalgoritmo", "logico", "retorne", 
		"caso", "fimenquanto", "maiusc", "se", 
		"compr", "fimescolha", "minusc", "senao", 
		"copia", "fimfuncao", "mod", "timer", 
		"cronometro", "fimpara", "nao", "var", 
		"debug", "fimprocedimento", "numpcarac", "vetor", 
		"e", "fimrepita", "ou", "verdadeiro", 
		"eco", "fimse", "outrocaso", "xou"
			
	};

	public VisualGScanner(VisualGColorManager manager) {
		//Detecção de cor associada ao tipo da palavra, nesse caso, palavras reservadas o VisualG
		Color color = manager.getColor(IVisualGColorConstants.RESERVED_WORD);
		TextAttribute attribute = new TextAttribute(color);
		IToken tokenReservedWords = new Token(attribute);

		WordRule rule = new WordRule(new VisualGWordDetector());
		for (String word : VG_RESERVED_WORDS) {
			rule.addWord(word, tokenReservedWords);
		}
		
		IRule[] rules = new IRule[1];
		rules[0] = rule;
		setRules(rules);
	}
}
