package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.*;

public class VisualGPartitionScanner extends RuleBasedPartitionScanner {
	
	public final static String VG_COMMENT = "__vg_comment";
	public final static String VG_STRING = "__vg_string";
	public final static String VG_RESERVED_WORD = "__vg_reserved_word";
	
	public VisualGPartitionScanner() {
		IToken visualGstring = new Token(VG_STRING);
		IToken visualGComment = new Token(VG_COMMENT);

		IPredicateRule[] rules = new IPredicateRule[2];
		//Defini��o das regras para os coment�rios e Strings
		rules[0] = new SingleLineRule("\"", "\"", visualGstring, '\\'); //String: express�es arrodeadas por (")
		rules[1] = new SingleLineRule("//", null, visualGComment, '\\');//Coment�rio: express�es que come�am com (//)

		setPredicateRules(rules);
	}
}
