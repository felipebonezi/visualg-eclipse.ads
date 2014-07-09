package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class VisualGScanner extends RuleBasedScanner {
	
	private static final String[] VG_RESERVED_WORDS = {
			"algoritmo",
			"inicio",
			"fim"
	};

	public VisualGScanner(VisualGColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IVisualGColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[1];
		//Add rule for processing instructions
//		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
//		rules[1] = new WhitespaceRule(new VisualGWhitespaceDetector());

		WordRule wordRule = new WordRule(new IWordDetector() {
			
			public boolean isWordStart(char c) {
				return Character.isLetter(c);
			}
			
			public boolean isWordPart(char c) {
				return Character.isLetter(c);
			}
			
		});
		for (String word : VG_RESERVED_WORDS) {
			wordRule.addWord(word, procInstr);
		}
		
		rules[0] = wordRule;
		
		setRules(rules);
	}
}
