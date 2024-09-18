package zeichenprogramm;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PaintPanelData {

	// Liste aller GraphikObjekte
	private List<Drawable> figurenliste;
	private Drawable inProgress;

	public PaintPanelData() {
		figurenliste = new LinkedList<Drawable>();
	}

	public void setInProgress(Drawable go) {
		this.inProgress = go;

	}

	public List<Drawable> getFigurenliste() {
		return figurenliste;
	}

	public Optional <Drawable> getInProgress() {
		return Optional.ofNullable(inProgress);
	}

	public void add(Drawable go) {
		figurenliste.add(go);
	}

}
