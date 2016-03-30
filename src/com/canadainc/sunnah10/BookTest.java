package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	@Test
	public void testHashCode()
	{
		Book b1 = new Book(1, "B1");
		Book b2 = new Book(1, "B1");
		assertEquals( b1.hashCode(), b2.hashCode() );

		b2.name = "LSDKJF";
		assertEquals( b1.hashCode(), b2.hashCode() );

		b2.transliteration = "KJKJK";
		assertEquals( b1.hashCode(), b2.hashCode() );

		b2.id = 6;
		assertNotEquals( b1.hashCode(), b2.hashCode() );
	}

	@Test
	public void testEqualsObject()
	{
		Book b1 = new Book(1, "B1");
		Book b2 = new Book(1, "B1");
		assertEquals(b1, b2);

		b2.name = "LSDKJF";
		assertNotEquals(b1, b2);

		b2.name = "B1";
		b2.transliteration = "KJKJK";
		assertNotEquals(b1, b2);

		b2.id = 6;
		b2.transliteration = null;
		assertNotEquals(b1, b2);
	}

	@Test
	public void testCompareTo()
	{
		Book b1 = new Book(1, "B1");
		Book b2 = new Book(1, "B1");
		assertEquals( 0, b1.compareTo(b2) );

		b2.name = "LSDKJF";
		assertEquals( 0, b1.compareTo(b2) );

		b2.name = "B1";
		b2.transliteration = "KJKJK";
		assertEquals( 0, b1.compareTo(b2) );

		b2.id = 6;
		assertEquals( -1, b1.compareTo(b2) );
		assertEquals( 1, b2.compareTo(b1) );
	}

	@Test
	public void testToString()
	{
		Book b1 = new Book(1, "B1");
		assertEquals( "1: B1", b1.toString() );

		b1.transliteration = "X";
		assertEquals( "1: B1 (X)", b1.toString() );
	}
}