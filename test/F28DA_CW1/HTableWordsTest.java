package F28DA_CW1;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class HTableWordsTest {

	@Test
	public void test1() {
		HTWordsSet h = new HTWordsSet();
        String w1 = "A";
        String w2 = "B";
        String w3 = "C";
        String w4 = "D";
        
 
            try {
                h.insWord(w1);
                h.insWord(w2);
                h.insWord(w3);
            } catch (SpellCheckException e) {
//               TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        assertEquals(3, h.getWordsCount());
        assertEquals("C", w3);
        assertTrue(h.wordExists(w1));
        assertFalse(h.wordExists(w4));
    }   
		
		
	@Test
	public void test2() {				//test taken from test8 of HTWordSetProvidedTest
		float maxLF = (float) 0.5;
		HTWordsSet h = new HTWordsSet(maxLF);
		LinkedList<String> l = new LinkedList<String>();
		String word;
		try {
			for (int k = 0; k < 1000; k++) {
				word = "w" + k;
				h.insWord(word);
				l.add(word);
			}
			for (int k = 100; k < 300; k++) {
				word = "w" + k;
				h.rmWord(word);
				l.remove(word);
			}
		} catch(SpellCheckException e) {
			fail();
		}
		Iterator<String> all = h.getWordsIterator();
		while (all.hasNext()) {
			assertTrue(l.remove(all.next()));
		}
		assertEquals(l.size(), 0);
	}
	}

	// ...

