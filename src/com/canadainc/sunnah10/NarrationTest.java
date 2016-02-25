package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import org.junit.Test;

public class NarrationTest {

	@Test
	public void testHashCode()
	{
		Narration n1 = new Narration(1,"babName", 5, 2, "bookName", "hadithNumber", 3, 16, "text");
		Narration n2 = new Narration(1,"babName", 5, 2, "bookName", "hadithNumber", 3, 16, "sxxxd");
		
		assertEquals( n1.hashCode(), n2.hashCode() );
		assertEquals( n1.hashCode(), n1.hashCode() );
		
		n2.id = 555;
		assertNotEquals( n1.hashCode(), n2.hashCode() );
	}

	@Test
	public void testEqualsObject()
	{
		Narration n1 = new Narration(1,"babName", 5, 2, "bookName", "hadithNumber", 3, 16, "text");
		Narration n2 = new Narration(1,"babName", 5, 2, "bookName", "hadithNumber", 3, 16, "text");
		assertEquals(n1, n2);
		assertEquals(n1, n1);
		
		n2.text = "";
		assertNotEquals(n1, n2);
	}
}