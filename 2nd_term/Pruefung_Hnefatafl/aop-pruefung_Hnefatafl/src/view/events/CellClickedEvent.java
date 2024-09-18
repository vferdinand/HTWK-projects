package view.events;

public class CellClickedEvent extends ViewEvent {
	private int x;
	private int y;

	public CellClickedEvent(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(final CellClickedEvent other) {
		if (this.x == other.x && this.y == other.y) {
			return true;
		}

		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
