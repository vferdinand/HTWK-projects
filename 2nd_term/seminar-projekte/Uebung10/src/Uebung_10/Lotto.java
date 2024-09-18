package Uebung_10;

public class Lotto {

	private LottoDaten model;
	private LottoGUI view;

	public Lotto() {
		model = new LottoDaten();
		view = new LottoGUI(model);

	}

	public static void main(String[] args) {
		Lotto controller = new Lotto();
		controller.view.setVisible(true);
	}

}
