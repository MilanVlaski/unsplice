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
	void shouldGetIndexOneIfSlashThenChar() {
		
		Unsplicer unsplicer = new Unsplicer("\\a");
		
		assertEquals(2, unsplicer.resolveSlash(0));
	}
	
	@DisplayName ("FALSE POSITIVE : UNIMPLEMENTED")
	@Test
	void shouldNotStoreIfSlashAndNewline() {
		
		Unsplicer unsplicer = new Unsplicer("\\\n");
		Vector<Character> expected = new Vector<Character>();	
		
		unsplicer.resolveSlash(0);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldStoreOneSlash() {
		
		Unsplicer unsplicer = new Unsplicer("\\");
		Vector<Character> expected = new Vector<Character>();	
		expected.add('\\');
		
		unsplicer.resolveSlash(0);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldStoreSlashAndChar() {
		
		Unsplicer unsplicer = new Unsplicer("\\a");
		Vector<Character> expected = new Vector<Character>();	
		expected.add('\\');
		expected.add('a');
		
		unsplicer.resolveSlash(0);
		
		assertEquals(expected, unsplicer.getResult());
	}
	
	@Test
	void shouldStoreZerothAndFirstChar() {
		
		Unsplicer unsplicer = new Unsplicer("abc");
		Vector<Character> expected = new Vector<Character>();	
		expected.add('a');
		expected.add('b');
		
		unsplicer.storeFromTo(0, 1);
		
		assertEquals(expected, unsplicer.getResult());
		
	}
}
