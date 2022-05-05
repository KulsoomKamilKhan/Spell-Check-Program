package F28DA_CW1;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.*;

public class LLWordsSet implements WordsSet {
	private LinkedList<String> list ;
	
	
	public LLWordsSet() 
	{
		list = new LinkedList<String>();	
	}
	
	public void insWord(String word) throws SpellCheckException
	{
		for(String s : list)
		{
			if(s.equals(word))
				throw new SpellCheckException("This word already exists in the list");	
	    }
		list.add(word);
	}
	
	
	public void rmWord(String word) throws SpellCheckException
	{
		for(String s : list)
		{
			if(s.equals(word))
				list.remove();	
			return;
	    }
		throw new SpellCheckException("No such word exists in list");
	}
	
	
	public boolean wordExists(String word)
	{	
		for(String s : list)
		{
			if(s.equals(word))
				return true;
		}	
		return false;
		}
	
	
	public int getWordsCount()
	{
		return list.size();
	}
	
	
	public Iterator<String> getWordsIterator()
	{
		return list.iterator();
	}
}
