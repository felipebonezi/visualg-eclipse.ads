package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class VisualGScanner extends RuleBasedScanner {
	
	String[] VG_RESERVED_WORDS;

	public VisualGScanner(VisualGColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IVisualGColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new VisualGWhitespaceDetector());

		setRules(rules);
	}
}
