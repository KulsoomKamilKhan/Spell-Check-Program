package F28DA_CW1;
 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
 
/**
 * Main class for the program
 */
public class SpellCheck {
	
	public static void main(String[] args) throws SpellCheckException {
	
	if (args.length != 2) {
		System.err.println("SpellCheck dictionaryFile.txt text-to-check.txt ");
		System.exit(1);
	}

	try {

		BufferedInputStream dict, file;
		
		dict = new BufferedInputStream(new FileInputStream(args[0])); 
		
		long currtime = System.currentTimeMillis();
		
		FileWordRead dictRead = new FileWordRead(dict);
		
		HTWordsSet dictText = new HTWordsSet();
//		LLWordsSet dictText = new LLWordsSet();
		
		while (dictRead.hasNextWord()) 
		{
			String next = dictRead.nextWord();
			
			try {
				dictText.insWord(next); 
			} catch (SpellCheckException e) {
				e.getSuppressed();
			}
		}
		
		SpellingImpl speller = new SpellingImpl();
		
		long lastTime = System.currentTimeMillis();
		
		System.out.println("Number of Words in the dictionary:" + dictText.getWordsCount());
		
		dict.close();
		

		/////// ^^^^ reading dict file ^^^^
		
		////// reading second file to check if word exists 
		
		file = new BufferedInputStream(new FileInputStream(args[1])); 
		
		FileWordRead fileRead = new FileWordRead(file);

		while (fileRead.hasNextWord()) 
		{
			String nextWord = fileRead.nextWord(); 
			
			if (dictText.wordExists(nextWord) == false) 
			{
				Iterator<String> it = speller.suggestions(nextWord, dictText).getWordsIterator(); //using iterator to read all words
																								  //in Hash set or Linked List and run through spelling suggestions
				System.out.print(nextWord + " => "); 
				while (it.hasNext()) { 
					System.out.print(it.next() + ", ");
					
					if(it.hasNext()) 
					{ 
						System.out.print(", ");
					}
					System.out.println(". \n");
				}
				
			}
		}
		
		file.close();
		
		long runningTime = lastTime - currtime;
		
		System.out.println(runningTime + "ms");
		
	} catch (IOException e) { 		//catching input output exceptions
		System.err.println("Missing input file, check your filenames");
		System.exit(1);
	}
}
 
    }
 
