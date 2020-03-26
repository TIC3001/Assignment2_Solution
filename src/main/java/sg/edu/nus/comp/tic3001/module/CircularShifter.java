package sg.edu.nus.comp.tic3001.module;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sg.edu.nus.comp.tic3001.event.LineStorageChangeEvent;
import sg.edu.nus.comp.tic3001.model.Line;
import sg.edu.nus.comp.tic3001.model.LineStorage;

public class CircularShifter implements Observer {
	final private LineStorage resultStorage;
	private Set<String> ignoreWords = new HashSet<>();
	private Set<String> requiredWords = new HashSet<>();

	public CircularShifter(LineStorage storage) {
		resultStorage = storage;
	}

	public void setIgnoreWords(Set<String> ignoreWords) {
		this.ignoreWords = ignoreWords;
	}

	public void setRequiredWords(Set<String> requiredWords) {
		System.out.println(requiredWords.size());
		this.requiredWords = requiredWords;
	}
	@Override
	public void update(Observable o, Object arg) {
		LineStorage storage = (LineStorage) o;
		LineStorageChangeEvent event = (LineStorageChangeEvent) arg;
		switch (event.getEventType()) {
		case ADD:
			Line line = storage.get(event.getChangedLine());
			doShift(line);
			break;
		default:
			break;
		}
	}

	private void doShift(Line line) {
		for (int i = 0; i < line.size(); i++) {
			// Ignore shift starting with ignored word.
			if (isIgnoreWord(line.getWord(i))) {
				continue;
			}
			if (isRequiredWord(line.getWord(i))) {
				String newLine = Stream
						.concat(line.getWordsFromIndexToEnd(i).stream(), line.getWordsFromStartToIndex(i).stream())
						.collect(Collectors.joining(" "));
				resultStorage.addLine(newLine);
			}
		}
	}

	private boolean isIgnoreWord(String word) {
		return ignoreWords.contains(word.toLowerCase());
	}

	private boolean isRequiredWord(String word) { return (requiredWords.contains(word.toLowerCase()) || requiredWords.size() == 0); }
}
