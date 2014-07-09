package visualgeclipse.ads.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.swt.widgets.Display;

public class VisualGReconcilingStrategy implements IReconcilingStrategy {

	private IDocument mDocument;
	private VisualGEditor mEditor;
	private List<Position> mPositions;
	private int mOffset;
	private int mRangeEnd;

	public VisualGReconcilingStrategy(VisualGEditor editor) {
		this.mEditor = editor;
		this.mPositions = new ArrayList<Position>();
	}

	@Override
	public void setDocument(IDocument document) {
		this.mDocument = document;

	}

	@Override
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		initialReconcile();
	}

	@Override
	public void reconcile(IRegion partition) {
		initialReconcile();
	}

	public void setProgressMonitor(IProgressMonitor monitor) {
	}

	private void initialReconcile() {
		mOffset = 0;
		mRangeEnd = mDocument.getLength();
		calculatePositions();
	}

	private void calculatePositions() {
		mPositions.clear();

		List<String> words = Arrays.asList(mDocument.get().split(" "));
		recursiveTokens(words);

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				mEditor.updateFoldingStructure(mPositions);
			}

		});
	}

	private int recursiveTokens(List<String> words) {
		return 0;
	}

	private void rangePosition(int startOffset, int length) {
		mPositions.add(new Position(startOffset, length));
	}

}
