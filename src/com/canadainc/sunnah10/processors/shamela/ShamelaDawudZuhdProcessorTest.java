package com.canadainc.sunnah10.processors.shamela;

import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaDawudZuhdProcessorTest
{
	@Test
	public void testProcess() throws Exception
	{
		ShamelaProcessor s = new ShamelaDawudZuhdProcessor();

		SunnahTestUtils.loadAndAssertSize("zuhd_dawud/012.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 11, null,
				"حَدَّثَنَا أَبُو دَاوُدَ قَالَ نا", "ص: 38", " إِلَى رَأْسِ صَاحِبِهِ");

		SunnahTestUtils.loadAndAssertSize("zuhd_dawud/553.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 502, null,
				"بِالْأَصَابِعِ فِي الشَّرِّ");
	}
}
