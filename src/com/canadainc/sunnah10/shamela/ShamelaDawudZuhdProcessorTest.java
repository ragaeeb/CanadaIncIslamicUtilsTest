package com.canadainc.sunnah10.shamela;

import java.io.IOException;

import org.junit.Test;

public class ShamelaDawudZuhdProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaDawudZuhdProcessor();

		ShamelaTestUtils.loadAndAssertSize("zuhd_dawud/012.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 11, null,
				"حَدَّثَنَا أَبُو دَاوُدَ قَالَ نا", "ص: 38", " إِلَى رَأْسِ صَاحِبِهِ");

		ShamelaTestUtils.loadAndAssertSize("zuhd_dawud/553.txt", s, 2);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 502, null,
				"بِالْأَصَابِعِ فِي الشَّرِّ");
	}
}
