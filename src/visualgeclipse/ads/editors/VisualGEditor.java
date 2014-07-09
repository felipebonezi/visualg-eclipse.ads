package visualgeclipse.ads.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class VisualGEditor extends TextEditor {
	
	private VisualGColorManager colorManager;

	public VisualGEditor() {
		super();
		colorManager = new VisualGColorManager();
		setSourceViewerConfiguration(new VisualGSourceViewerConfiguration(colorManager));
		setDocumentProvider(new VisualGDocumentProvider());
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
