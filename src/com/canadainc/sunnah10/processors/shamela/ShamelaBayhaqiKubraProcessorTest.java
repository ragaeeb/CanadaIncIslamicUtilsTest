package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaBayhaqiKubraProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaBayhaqiKubraProcessor();
	}

	@Test
	public void ignoreChapters() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("bayhaqi_kubra/18422.txt", s, 0);
	}

	
	@Test
	public void ignoreBooks() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("bayhaqi_kubra/01679.txt", s, 0);
	}
}
