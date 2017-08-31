package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaTaarikhBaghdadiProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaTaarikhBaghdadiProcessor();
	}

	@Test
	public void standard() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("taarikh_baghdadi/2167.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1739, null,
				"محمد بن هشام بن عيسى", "سنة اثنتين وخمسين، يعني ");		
	}
}