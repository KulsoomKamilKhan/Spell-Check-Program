package F28DA_CW1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModificationsTest {

	@Test
	public void testOmit() {
		WordsSet dict = new LLWordsSet(); // or new HTWordsSet();
	
		try {
			dict.insWord("test");
		} catch (SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("teest", dict);
		assertTrue(sugg.wordExists("test"));
	}

	@Test
	public void testSwap() {
		WordsSet dict = new HTWordsSet(); 
		
		try {
			dict.insWord("test");
		} catch (SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("etst", dict);
		assertTrue(sugg.wordExists("test"));
	}

	
	@Test
	public void testSub() {
		WordsSet dict = new HTWordsSet(); 
		
		try {
			dict.insWord("test");
		} catch (SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("teft", dict);
		assertTrue(sugg.wordExists("test"));
	}
	
	@Test
	public void testIns() {
		WordsSet dict = new HTWordsSet(); 
		
		try {
			dict.insWord("test");
		} catch (SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("tst", dict);
		assertTrue(sugg.wordExists("test"));
	}

}
