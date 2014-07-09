package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;

public class VisualGPartitionScanner extends RuleBasedPartitionScanner {
	
	public final static String VG_COMMENT = "__vg_comment";
	public final static String VG_STRING = "__vg_string";
	public final static String VG_RESERVED_WORD = "__vg_reserved_word";
	
	public VisualGPartitionScanner() {
		IToken visualGComment = new Token(VG_COMMENT);
		IToken visualGstring = new Token(VG_STRING);
		IToken visualGReservedWord = new Token(VG_RESERVED_WORD);

		IPredicateRule[] rules = new IPredicateRule[1];

		rules[0] = new MultiLineRule("<!--", "-->", visualGComment);
		//rules[1] = new TagRule(string);

		setPredicateRules(rules);
	}
}
