package visualgeclipse.ads.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class VisualGEditor extends TextEditor {
	
	VisualGScanner mScanner;

	private VisualGColorManager colorManager;

	public VisualGEditor() {
		super();
		colorManager = new VisualGColorManager();
		setSourceViewerConfiguration(new VisualGConfiguration(colorManager));
		setDocumentProvider(new VisualGDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
