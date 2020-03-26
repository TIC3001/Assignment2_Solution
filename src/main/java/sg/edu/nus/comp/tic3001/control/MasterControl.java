package sg.edu.nus.comp.tic3001.control;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sg.edu.nus.comp.tic3001.model.LineStorage;
import sg.edu.nus.comp.tic3001.module.Sorter;
import sg.edu.nus.comp.tic3001.module.CircularShifter;

public class MasterControl {
	final private Sorter sorter;
	final private CircularShifter shifter;

	private LineStorage rawInputLines;
	private LineStorage resultLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		resultLines = new LineStorage();

		// Sub-modules
		shifter = new CircularShifter(resultLines);
		sorter = new Sorter();

		// Set up observation
		rawInputLines.addObserver(shifter);
		resultLines.addObserver(sorter);
	}

	public List<String> run(List<String> input, Set<String> ignoredWords, Set<String> requiredWords) {
		rawInputLines.clearLines();
		resultLines.clearLines();

		// Set ignore words (make them lowercase for comparison)
		shifter.setIgnoreWords(this.transformSetToLowercase(ignoredWords));

		// Set required words
		shifter.setRequiredWords(this.transformSetToLowercase(requiredWords));

		// Add data line by line
		for (String line : input) {
			rawInputLines.addLine(line);
		}
		
		return resultLines.getAll();
	}
	
	private Set<String> transformSetToLowercase(Set<String> set) {
		return set.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
	}
}
