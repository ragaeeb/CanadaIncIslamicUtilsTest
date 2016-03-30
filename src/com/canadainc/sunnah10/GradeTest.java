package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import org.junit.Test;

public class GradeTest
{
	@Test
	public void testHashCode()
	{
		assertEquals( new Grade(1, "Sahih").hashCode(), new Grade(1, "Sahih").hashCode() );
		assertNotEquals( new Grade(2, "Sahih").hashCode(), new Grade(1, "Sahih").hashCode() );
		assertNotEquals( new Grade(1, "Sahih2").hashCode(), new Grade(1, "Sahih").hashCode() );
	}

	@Test
	public void testEqualsObject()
	{
		assertEquals( new Grade(1, "Sahih"), new Grade(1, "Sahih") );
		assertNotEquals( new Grade(2, "Sahih"), new Grade(1, "Sahih") );
		assertNotEquals( new Grade(1, "Sahih2"), new Grade(1, "Sahih") );
	}

	@Test
	public void testToString()
	{
		assertEquals( "1: Sahih", new Grade(1, "Sahih").toString() );
	}
}