package Uebung_09;

public class NoteBook {

	private NoteBookGUI view;
	private NoteBookData data;
	
	public NoteBook() {
		data = new NoteBookData();
		view = new NoteBookGUI(data);
	}
	
	public static void main(String[] args) {
		
		NoteBook notebook = new NoteBook();
		notebook.view.setVisible(true);
	}
}
