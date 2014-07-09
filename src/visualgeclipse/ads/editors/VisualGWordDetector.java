package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class VisualGWordDetector implements IWordDetector {

	public boolean isWordStart(char c) {
		return Character.isLetter(c) || c == '\\';
	}
	
	public boolean isWordPart(char c) {
		return Character.isLetter(c) || c == '\\';
	}

}
