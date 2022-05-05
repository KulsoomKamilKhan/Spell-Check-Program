package F28DA_CW1;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellingImpl implements Spelling {

	
	public WordsSet suggestions(String word, WordsSet dict) {
		
		HTWordsSet suggests = new HTWordsSet();
		
		
		String nWord;	
		StringBuffer buff = new StringBuffer(word); 
		
		
		//letter substitution
		for (int i=0; i < word.length(); i++) {
			for (char lett='a'; lett <= 'z'; lett++) {
				
				nWord = buff.replace(i, i+1, String.valueOf(lett)).toString();	//replacing every letter with another letter or substituting it
				
					if (!suggests.wordExists(nWord)) 	//if it does not exist in hash set, add it 
					{
						try {
							suggests.insWord(nWord);
						} catch (SpellCheckException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				buff = new StringBuffer(word);
			}
		}
		
		
		//letter omission
				for(int i = 0; i < word.length(); i++) {
				    nWord = word.substring(0, i) + word.substring(i+1, word.length());	//removing a letter to check 
				    if(dict.wordExists(nWord)) {										//if the word exists in hash set
				    	try {
				    		suggests.insWord(nWord);
				    	} catch (SpellCheckException e) {}
				    }
				}
			
				
	
		//letter insertion	
				for (int i = 0; i <= word.length(); i++) {
					for (char c = 'a'; c <= 'z'; c++) {	
						nWord = word.substring(0, i) + c + word.substring(i, word.length());	//adding a letter to the word
						
						if(dict.wordExists(nWord)) {
							try {
								suggests.insWord(nWord);
							} catch (SpellCheckException e) {}
						}
					}
				 }
		 
				
				
		 //letter reversal
		 StringBuffer swap = new StringBuffer();
			
			for (int i=0; i<word.length()-1; i++) {
				swap = new StringBuffer(buff.substring(i, i+2)).reverse();	//swapping letters until the word is correct
				buff.delete(i,i+2);
				nWord = buff.insert(i, swap).toString();
				
					if (!suggests.wordExists(nWord)) {
						try {
							suggests.insWord(nWord);
						} catch (SpellCheckException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				
				//Resetting buffer
				buff = new StringBuffer(word);
			}
		
		return suggests;	
		
    }
		
        }
	
	


