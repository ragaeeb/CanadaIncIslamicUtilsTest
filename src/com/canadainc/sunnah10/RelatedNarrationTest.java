package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;

import org.junit.Test;

public class RelatedNarrationTest
{
	@Test
	public void testHashCode()
	{
		RelatedNarration r1 = new RelatedNarration(2,3);
		RelatedNarration r2 = new RelatedNarration(3,2);
		
		assertEquals(r1.hashCode(), r2.hashCode());
		
		r2.narrationId = 5;
		assertNotEquals(r1.hashCode(), r2.hashCode());
		
		r2.narrationId = 3;
		
		HashSet<RelatedNarration> hm = new HashSet<RelatedNarration>();
		hm.add(r1);
		hm.add(r2);
		assertEquals( 1, hm.size() );
	}

	@Test
	public void testEqualsObject()
	{
		RelatedNarration r1 = new RelatedNarration(2,3);
		RelatedNarration r2 = new RelatedNarration(3,2);
		
		assertEquals(r1, r1);
		assertEquals(r2, r2);
		assertEquals(r1, r2);
		
		r2.narrationId = 5;
		assertNotEquals(r1, r2);
	}
}