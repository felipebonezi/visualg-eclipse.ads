package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;
import org.eclipse.swt.graphics.Color;

public class VisualGScanner extends RuleBasedScanner {
	
	private static final String[] VG_RESERVED_WORDS = {
			"algoritmo",
			"inicio",
			"fimalgoritmo"
	};

	public VisualGScanner(VisualGColorManager manager) {
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
