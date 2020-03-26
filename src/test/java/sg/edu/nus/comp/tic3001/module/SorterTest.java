package sg.edu.nus.comp.tic3001.module;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.tic3001.model.LineStorage;

public class SorterTest {
	LineStorage storage;
	Sorter sorter;

	@Before
	public void setUp() {
		storage = new LineStorage();
		sorter = new Sorter();
		storage.addObserver(sorter);
	}

	@Test
	public void test() {
		storage.addLine("ghi abc def");
		storage.addLine("def ghi abc");
		storage.addLine("abc def ghi");

		assertEquals(3, storage.size());
		assertEquals("abc def ghi", storage.get(0).toString());
		assertEquals("def ghi abc", storage.get(1).toString());
		assertEquals("ghi abc def", storage.get(2).toString());
	}

}
