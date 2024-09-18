package view.events;

public class ButtonClickedEvent extends ViewEvent {
	public static final ButtonClickedEvent PLACE_ATTACKER = new ButtonClickedEvent();
	public static final ButtonClickedEvent PLACE_DEFENDER = new ButtonClickedEvent();
	public static final ButtonClickedEvent REMOVE_PIECE = new ButtonClickedEvent();
}
