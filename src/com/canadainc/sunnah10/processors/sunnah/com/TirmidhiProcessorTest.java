package com.canadainc.sunnah10.processors.sunnah.com;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class TirmidhiProcessorTest
{
	private TirmidhiProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new TirmidhiProcessor();
	}

	@Test
	public void correctedDuplicate() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_com/english/tirmidhi/47.txt", s, 424);
		assertEquals( "2954", SunnahTestUtils.getNarration(s, 639380).hadithNumber );
		assertEquals( -1, s.getTypos().getIndex(3954) );
		
		SunnahTestUtils.loadAndAssertSize("sunnah_com/english/tirmidhi/49.txt", s, 776);
		assertEquals( "3954", SunnahTestUtils.getNarration(s, 638490).hadithNumber );
	}
	
	
	@Test
	public void breakUp() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_com/arabic/tirmidhi/3.txt", s, 36);
		assertEquals( "457", SunnahTestUtils.getNarration(s, 704600).hadithNumber );
		assertEquals( "458", SunnahTestUtils.getNarration(s, 704600).hadithNumber );
	}
}