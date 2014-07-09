package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;

public class VisualGPartitionScanner extends RuleBasedPartitionScanner {
	
	public final static String VG_COMMENT = "__vg_comment";
	public final static String VG_STRING = "__vg_string";
	public final static String VG_RESERVED_WORD = "__vg_reserved_word";
	
	public VisualGPartitionScanner() {
		IToken visualGstring = new Token(VG_STRING);

		IPredicateRule[] rules = new IPredicateRule[1];

		rules[0] = new SingleLineRule("\"", "\"", visualGstring, '\\');

		setPredicateRules(rules);
	}
}
