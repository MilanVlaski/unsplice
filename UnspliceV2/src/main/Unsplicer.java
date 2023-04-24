package main;

import java.util.List;
import java.util.Vector;

public class Unsplicer {

	String word;
	List<Character> result;
	
	public Unsplicer(String word) {
		
		this.word = word;
		this.result = new Vector<Character>();
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}


	public List<Character> getResult() {
		return result;
	}
	public void setResult(List<Character> result) {
		this.result = result;
	}
	
	public void store(int i) {
		this.result.add(word.charAt(i));
	}
	
	public int resolveSlash(int indexOfSlash) {
		
		int j;
		
		for(j = indexOfSlash ; j < word.length(); j++) {
			
			if(word.charAt(j) == '\n') {
				j++;
				break;
			}	
			else if(Character.isAlphabetic(word.charAt(j))) {
				storeFromTo(indexOfSlash, j);
				j++;
				break;	
			}
			else if (j == word.length() - 1) {
				storeFromTo(indexOfSlash, j);
				j++;
				break;
			}
		}
		
		return j;
	}

	public void storeFromTo(int i, int j) {
		for(int k = i; k <= j; k++)
			store(k);
	}
	
	
}
