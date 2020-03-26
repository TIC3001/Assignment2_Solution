package sg.edu.nus.comp.tic3001.event;

public class LineStorageChangeEvent extends BaseEvent {
	public enum LineStorageChangeType {
		ADD;
	}

	final private LineStorageChangeType type;
	final private int line;

	public LineStorageChangeEvent(LineStorageChangeType type, int line) {
		super(LineStorageChangeEvent.class.getName());
		this.type = type;
		this.line = line;
	}

	public LineStorageChangeType getEventType() {
		return type;
	}

	public int getChangedLine() {
		return line;
	}
}
