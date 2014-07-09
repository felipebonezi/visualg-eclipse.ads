package visualgeclipse.ads.editors;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;

public class VisualGEditor extends TextEditor {

	private VisualGColorManager colorManager;
	private ProjectionAnnotationModel annotationModel;
	private ProjectionSupport ps;
	private Annotation[] oldAnnotations;

	public VisualGEditor() {
		super();
		colorManager = new VisualGColorManager();
		setSourceViewerConfiguration(new VisualGSourceViewerConfiguration(this,
				colorManager));
		setDocumentProvider(new VisualGDocumentProvider());
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
		ps = new ProjectionSupport(viewer, getAnnotationAccess(),
				getSharedColors());
		ps.install();

		viewer.doOperation(ProjectionViewer.TOGGLE);
		annotationModel = viewer.getProjectionAnnotationModel();
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		ISourceViewer viewer = new ProjectionViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles);
		getSourceViewerDecorationSupport(viewer);

		return viewer;
	}

	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

	public void updateFoldingStructure(ArrayList<Position> positions) {
		Annotation[] annotations = new Annotation[positions.size()];
		HashMap<ProjectionAnnotation, Position> newAnnotations = new HashMap<ProjectionAnnotation, Position>();

		for (int i = 0; i < positions.size(); i++) {
			ProjectionAnnotation annotation = new ProjectionAnnotation();
			newAnnotations.put(annotation, positions.get(i));
			annotations[i] = annotation;
		}

		annotationModel.modifyAnnotations(oldAnnotations, newAnnotations, null);
		oldAnnotations = annotations;
	}

}
