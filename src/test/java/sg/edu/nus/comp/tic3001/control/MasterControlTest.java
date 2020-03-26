package sg.edu.nus.comp.tic3001.control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MasterControlTest {

	MasterControl master;
	
	@Before
	public void setUp() {
		master = new MasterControl();
	}

	@Test
	public void testExample1() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		List<String> input = new ArrayList<>();
		input.add("the Day after Tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, new HashSet<>());

		assertEquals(6, result.size());
		assertEquals("Day after Tomorrow the", result.get(0));
		assertEquals("Fast and Furious", result.get(1));
		assertEquals("Furious Fast and", result.get(2));
		assertEquals("Man of Steel", result.get(3));
		assertEquals("Steel Man of", result.get(4));
		assertEquals("Tomorrow the Day after", result.get(5));
	}
	
	@Test
	public void testExample2() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("of");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("the");
		requiredWords.add("And");

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(2, result.size());
		assertEquals("The day after tomorrow", result.get(1));
		assertEquals("and Furious Fast", result.get(0));
	}
}
