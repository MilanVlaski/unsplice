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

	public List<Character> getResult() {
		return result;
	}
	
	public void store(int i) {
		this.result.add(word.charAt(i));
	}
	//Given position of slash in word, stores or doesn't store
	//the appropriate characters. Returns and index from which you can
	//continue parsing
	public int resolveSlash(int indexOfSlash) {
		
		int j;
		
		for(j = indexOfSlash ; j < word.length(); j++) {
			
			if(word.charAt(j) == '\n') {
				break;
			}
			else if(Character.isAlphabetic(word.charAt(j) ) || Character.isDigit(word.charAt(j))) {
				storeFromTo(indexOfSlash, j);
				break;	
			}
			else if (j == word.length() - 1) {
				storeFromTo(indexOfSlash, j);
				break;
			}
		}
		
		return j;
	}
	//Stores chars from position i to position j, including i and j
	public void storeFromTo(int i, int j) {
		for(int k = i; k <= j; k++)
			store(k);
	}

	public String unsplice() {
		
		int i = 0;
		while(i < word.length()){
			
			if(word.charAt(i) == '\\')
				i = resolveSlash(i);
			else
				store(i);
			
			i++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (Character c : result)
			sb.append(c);
		
		
		return sb.toString();
	}
	
	
}
