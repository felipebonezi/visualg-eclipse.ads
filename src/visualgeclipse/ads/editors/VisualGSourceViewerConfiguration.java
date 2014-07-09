package visualgeclipse.ads.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class VisualGSourceViewerConfiguration extends SourceViewerConfiguration {

	private VisualGDoubleClickStrategy doubleClickStrategy;
	private VisualGScanner scanner;
	private VisualGColorManager colorManager;

	public VisualGSourceViewerConfiguration(VisualGColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			VisualGPartitionScanner.VG_COMMENT,
			VisualGPartitionScanner.VG_STRING,
			VisualGPartitionScanner.VG_RESERVED_WORD };
	}
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new VisualGDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected VisualGScanner getVisualGScanner() {
		if (scanner == null) {
			scanner = new VisualGScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IVisualGColorConstants.DEFAULT))));
		}
		return scanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

//		DefaultDamagerRepairer dr =
//			new DefaultDamagerRepairer(getXMLTagScanner());
//		reconciler.setDamager(dr, XMLPartitionScanner.XML_TAG);
//		reconciler.setRepairer(dr, XMLPartitionScanner.XML_TAG);

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getVisualGScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IVisualGColorConstants.COMMENT)));
		reconciler.setDamager(ndr, VisualGPartitionScanner.VG_COMMENT);
		reconciler.setRepairer(ndr, VisualGPartitionScanner.VG_COMMENT);

		return reconciler;
	}

}