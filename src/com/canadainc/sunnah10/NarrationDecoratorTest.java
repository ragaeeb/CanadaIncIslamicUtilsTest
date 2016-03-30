package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NarrationDecoratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecorate() {
	}

	@Test
	public void testCorrectHadithBody()
	{
	}

	@Test
	public void testCorrectHadithNumber()
	{
		Narration n = new Narration();
		n.hadithNumber = "Introduction 1";
		
		NarrationCollector nd = new NarrationCollector();
		nd.correctHadithNumber(n);
		assertEquals( "1", n.hadithNumber );
		
		n.hadithNumber = "569a";
		nd.correctHadithNumber(n);
		assertEquals( "569a", n.hadithNumber );
	}

	@Test
	public void testCorrectChapters()
	{
	}

	@Test
	public void testCorrectBook()
	{
	}
}