package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryTest
{
	@Test
	public void testCorrectTypos()
	{
		Dictionary d = new Dictionary();
		assertEquals( "I narrated that", d.correctTypos("I narated that") );
		assertEquals( "I narrated that", d.correctTypos("I narrated that") );
		assertEquals( "The Prophet ﷺ said", d.correctTypos("The Prophet (s.a.w) said") );
	}
}