package visualgeclipse.ads.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.Color;

public class VisualGSourceViewerConfiguration extends SourceViewerConfiguration {

	private VisualGEditor mEditor;
	private VisualGDoubleClickStrategy mDoubleClickStrategy;
	private VisualGScanner mScanner;
	private VisualGColorManager mColorManager;

	public VisualGSourceViewerConfiguration(VisualGEditor editor, VisualGColorManager colorManager) {
		this.mEditor = editor;
		this.mColorManager = colorManager;
		createScanner();
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			VisualGPartitionScanner.VG_COMMENT,
			VisualGPartitionScanner.VG_STRING,
			VisualGPartitionScanner.VG_RESERVED_WORD };
	}
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
		if (mDoubleClickStrategy == null) {
			mDoubleClickStrategy = new VisualGDoubleClickStrategy();
		}
		
		return mDoubleClickStrategy;
	}

	private void createScanner() {
		if (mScanner == null) {
			mScanner = new VisualGScanner(mColorManager);
			mScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						mColorManager.getColor(IVisualGColorConstants.DEFAULT))));
		}
	}

	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
        VisualGReconcilingStrategy strategy = new VisualGReconcilingStrategy();
        strategy.setEditor(mEditor);
        
        MonoReconciler reconciler = new MonoReconciler(strategy, false);
        return reconciler;
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(mScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		Color commentColor = mColorManager.getColor(IVisualGColorConstants.COMMENT);
		TextAttribute commentAttribute = new TextAttribute(commentColor);
		NonRuleBasedDamagerRepairer ndrC = new NonRuleBasedDamagerRepairer(commentAttribute);
		reconciler.setDamager(ndrC, VisualGPartitionScanner.VG_COMMENT);
		reconciler.setRepairer(ndrC, VisualGPartitionScanner.VG_COMMENT);
		
		Color stringColor = mColorManager.getColor(IVisualGColorConstants.STRING);
		TextAttribute stringAttribute = new TextAttribute(stringColor);
		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(stringAttribute);
		reconciler.setDamager(ndr, VisualGPartitionScanner.VG_STRING);
		reconciler.setRepairer(ndr, VisualGPartitionScanner.VG_STRING);

		return reconciler;
	}

}