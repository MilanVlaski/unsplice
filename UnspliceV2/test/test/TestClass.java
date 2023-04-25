package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Unsplicer;

class TestClass {

	Unsplicer unsplicer;
	
	@Test
	void shouldStoreFirstChar() {
		
		Unsplicer unsplicer = new Unsplicer("ab");
		unsplicer.store(0);
		
		assertEquals('a', unsplicer.getResult().get(0));
	}
	
	@Test
	void shouldGetIndexTwoIfSlashThenNewline() {
		
		Unsplicer unsplicer = new Unsplicer("\\\n");
		
		assertEquals(2, unsplicer.resolveSlash(0));
	}
	
	@Test
	void shouldGetIndexTwoAfterTwoSlashes() {
		
		Unsplicer unsplicer = new Unsplicer("\\\\");
		
		assertEquals(2, unsplicer.resolveSlash(0));
	}

	@Test
	void shouldGetIndexTwoIfSlashThenChar() {
		
		Unsplicer unsplicer = new Unsplicer("\\a");
		
		assertEquals(2, unsplicer.resolveSlash(0));
	}
	
	@Test
	void shouldNotStoreIfSlashAndNewline() {
		
		Vector<Character> expected = new Vector<Character>();	
		
		Unsplicer unsplicer = new Unsplicer("\\\n");
		unsplicer.resolveSlash(0);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldStoreTwoSlashes() {
		
		Vector<Character> expected = new Vector<Character>();	
		expected.add('\\');
		expected.add('\\');
		
		Unsplicer unsplicer = new Unsplicer("\\\\");
		unsplicer.resolveSlash(0);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldStoreSlashAndChar() {
		
		Vector<Character> expected = new Vector<Character>();	
		expected.add('\\');
		expected.add('a');
		
		Unsplicer unsplicer = new Unsplicer("\\a");
		unsplicer.resolveSlash(0);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldStoreZerothAndFirstChar() {
		
		Vector<Character> expected = new Vector<Character>();	
		expected.add('a');
		expected.add('b');
		
		Unsplicer unsplicer = new Unsplicer("abc");
		unsplicer.storeFromTo(0, 1);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldNotUnspliceOneSlash() {
		
		Unsplicer unsplicer = new Unsplicer("a\\b");
		
		assertEquals("a\\b", unsplicer.unsplice());
	}
	
	@Test
	void shouldUnspliceOneSlashAndNewline() {
		
		Unsplicer unsplicer = new Unsplicer("a\\\nb");
		
		assertEquals("ab", unsplicer.unsplice());
	}
	
	@Test
	void shouldNotUnspliceLettersAndDigits() {
		
		Unsplicer unsplicer = new Unsplicer("a1b2c3");
		
		assertEquals("a1b2c3", unsplicer.unsplice());
	}
}
