package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChapterTest
{
	@Test
	public void testHashCode()
	{
		Chapter b1 = new Chapter("B1",1);
		Chapter b2 = new Chapter("B1", 1);
		assertEquals( b1.hashCode(), b2.hashCode() );

		b2.title = "LSDKJF";
		assertNotEquals( b1.hashCode(), b2.hashCode() );

		b2.title = "B1";
		b2.number = 2;
		assertNotEquals( b1.hashCode(), b2.hashCode() );
	}

	@Test
	public void testEqualsObject()
	{
		Chapter b1 = new Chapter("B1",1);
		Chapter b2 = new Chapter("B1",1);
		assertEquals(b1, b2);

		b2.title = "LSDKJF";
		assertNotEquals(b1, b2);

		b2.title = "B1";
		b2.number = 2;
		assertNotEquals(b1, b2);
	}

	@Test
	public void testToString()
	{
		Chapter b1 = new Chapter("B1",1);
		assertEquals( "1 B1", b1.toString() );
	}

	@Test
	public void testCompareTo()
	{
		Chapter b1 = new Chapter("B1",1);
		Chapter b2 = new Chapter("B1", 1);
		assertEquals( 0, b1.compareTo(b2) );

		b2.title = "LSDKJF";
		assertEquals( 0, b1.compareTo(b2) );

		b2.number = 6;
		assertEquals( -1, b1.compareTo(b2) );
		assertEquals( 1, b2.compareTo(b1) );
	}
}