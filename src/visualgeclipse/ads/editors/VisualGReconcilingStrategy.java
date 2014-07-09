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

	private static final int EOR_TAG = 4;
	
	private IDocument mDocument;
	private VisualGEditor mEditor;

	private final ArrayList<Position> mPositions = new ArrayList<Position>();
	private int mOffset;
	private int mRangeEnd;
	private int cNextPos;

	public VisualGEditor getEditor() {
		return mEditor;
	}

	public void setEditor(VisualGEditor mEditor) {
		this.mEditor = mEditor;
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

	public void initialReconcile() {
		mOffset = 0;
		mRangeEnd = mDocument.getLength();
		calculatePositions();
	}

	private void calculatePositions() {
		mPositions.clear();
		cNextPos = mOffset;

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

	private void emitPosition(int startOffset, int length) {
		mPositions.add(new Position(startOffset, length));
	}

	private int classifyTag(String word) {
		return EOR_TAG;
	}


}
