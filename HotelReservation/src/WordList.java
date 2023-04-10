
public class WordList {
	private String[] words;
	int count;
	
	public WordList()
	{
		words = new String[2];
		for(int i = 0; i < words.length; i++)
		{
			words[i] = "";
		}
		count = 0;
	}
	
	public int addWord(String arg)
	{
		for(String word : words)
		{
			if(word.equals(arg))
				return count;
		}
		if(count == words.length)
		{
			String[] temp = new String[count*2];
			for(int i = 0; i < temp.length; i++)
			{
				temp[i] = "";
			}
			for(int i = 0; i < words.length; i++)
			{
				temp[i] = words[i];
			}
			temp[words.length] = arg;
			words = temp;
			count++;
		}
		else
		{
			words[count] = arg;
			count++;
		}
		
		return count;
	}
	
	public void removeWord(String arg)
	{
		for(int i = 0; i < words.length; i++)
		{
			if(words[i] == arg)
			{
				for(int j = i; j < words.length-1; j++)
				{
					words[j] = words[j+1]; 
				}
				words[words.length-1] = "";
				count--;
			}
		}
	}
	
	public int findWord(String arg)
	{
		for(int i = 0; i < words.length; i++)
		{
			if(words[i].equals(arg))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean equals(WordList other) {
	    if (words.length != other.count) {
	      return false;
	    } else {
	      for (int i = 0; i < words.length; i++) {
	        if (words[i] != other.words[i]) {
	          return false;
	        }
	      }
	    }
	    return true;
	  }

	  public String toString() {
	    String s = "There are " + count + " word" + ((words.length == 1)?"":"s") + " in the word list:\n";
	    for (String w : words) {
	      s = s + w + "\n";
	    }
	    return s;
	  }

	  public static void main(String[] args) {
	    WordList wl = new WordList();
	    wl.addWord("Dog");
	    System.out.print(wl);
	    wl.addWord("Dog");
	    System.out.print(wl);
	    wl.removeWord("Dog");
	    wl.addWord("Cat");
	    wl.addWord("Fish");
	    wl.addWord("Horse");
	    System.out.print(wl);
	    if (wl.findWord("Cat") >= 0)
	      System.out.println("Cat is in the word list");
	    if (wl.findWord("Dog") >= 0)
	      System.out.println("Dog is in the word list");
	    WordList myList = new WordList();
	    myList.addWord("Cat");
	    myList.addWord("Horse");
	    myList.addWord("Fish");
	    if (wl.equals(myList))
	      System.out.println("The two lists are the same");
	  }
}
