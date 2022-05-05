package F28DA_CW1;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class HTWordsSet implements WordsSet, Hashing, Monitor 
{
	private String[] hashArray;
	private float maxLF;
	private int size;
	private int numElems;
	private int numberOfProbes;
	private int numberOfOperations;
	private final String space = ""; 

	public HTWordsSet() 
	{
		size = 7; // to be doubled
		hashArray = new String[size];
		numElems = 0;
		numberOfProbes = 0;
		numberOfOperations = 0;
		maxLF = 0.5f;
	}

	public HTWordsSet(float maxLF) 
	{
		size = 7; // to be doubled
		hashArray = new String[size];
		numElems = 0;
		numberOfProbes = 0;
		numberOfOperations = 0;
		this.maxLF = maxLF;
	}

	public void insWord(String word) throws SpellCheckException 
	{
		if (getLoadFactor() >= getMaxLoadFactor())  //if the load factor is greater than or equal to the maximum value
        {
        	resize();								//resize the hash table
        } 
		int hash1 = hash1(word);	//linear probing
        int hash2 = hash2(word);    //double hashing 
        numberOfProbes++;
        
        while (hashArray[hash1] != null && !hashArray[hash1].equals(space)) {	//while the index is not null or an empty string
			if (hashArray[hash1].equals(word)) 		//if the word being inserted already exists, throw an exception
			{ 
				throw new SpellCheckException("This word already exists");
			}
			
			hash1 += hash2; 	//else probe the hash set
			hash1 %= size;
			numberOfProbes++;  //increase no. of probes
		}
        
		hashArray[hash1] = word;	//insert the word in hashArray[hash1]
		numElems++;
		numberOfOperations++;
	}
	
	
	public void rmWord(String word) throws SpellCheckException 
	{
		int hash1 = hash1(word);	//linear probing
        int hash2 = hash2(word); 	//double hashing
        
        numberOfOperations++;
        numberOfProbes++;
        
        while (hashArray[hash1] != null) {			//while the array is not null
			if (hashArray[hash1].equals(word)) { 	//if the word exists in the hash set
				hashArray[hash1] = space; 			//make it an empty string and reduce the size of the array
				numElems--; 
				return;
			}
			
			hash1 += hash2; 	//probe the hash set and increase no. of probes
			hash1 %= size;
			numberOfProbes++; 
		}
		throw new SpellCheckException(word + " not present.");
	}

	
	public boolean wordExists(String word) 
	{
		Iterator<String> iter = getWordsIterator();	
		
		while (iter.hasNext()) 	//using getWordsIterator to iterate over all the words in the hash set
		{ 
			String nWord = iter.next();	
			if (word.equals(nWord))  	//if the word exists, return true
			{ 
				numberOfOperations++;
				numberOfProbes++;
				return true; 
			}
		}
		return false;
	}

	
	public int getWordsCount() 
	{
		return numElems;
	}

	
	public Iterator<String> getWordsIterator() 
	{
		ArrayList <String> newArr = new ArrayList<String>(); //creating iterator over new array
		for(String s : hashArray) {
			if(s!=null && !s.equals(space)) {		//checking for not null and defunct in new array
				newArr.add(s);						//adding words to new array
			}
		}
		return newArr.iterator();
	}

	
	public int giveHashCode(String s) 
	{
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
		int a = s.charAt(i) - 47;
		hash = (hash * 33 + a) % size;		//polynomial accumulation 
		}
		return Math.abs(hash);
	}
	
	
	private int hash1(String s) 
	{
		int hash =	giveHashCode(s) % size;	//linear probing method
		return hash;
	}
	
	private int hash2(String s) 
	{
		int prime = 5;
		int hash2 = prime - (giveHashCode(s) % prime); 	//double hashing method
		return hash2;
	}
	
	
	public float getMaxLoadFactor() 
	{
		return maxLF;
	}

	
	public float getLoadFactor() 
	{
		return (float) numElems / size;
	}

	
	public float getAverageProbes() 
	{
		return (float) numberOfProbes / numberOfOperations;
	}
	
	
	private boolean Prime(int n) 
	{
		for (int i = 2; i < n; i++) {		
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	
	private void resize() throws SpellCheckException 
	{
		size *= 2;		//doubling the size of the hash set
		
		while(!Prime(size)) //checking if it's a prime no.
		{
			size++;
		}
		
		Iterator<String> hTable = getWordsIterator();
		
		String[] hashArray2 = new String[size];
		numElems = 0;
		hashArray = hashArray2;
			
		while(hTable.hasNext()) {
			try {
				insWord(hTable.next());
			} catch (SpellCheckException e) {}
		}
		
	}

}
