package visualgeclipse.ads.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class VisualGWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}