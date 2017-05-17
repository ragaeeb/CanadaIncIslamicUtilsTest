package com.canadainc.sunnah10.processors.shamela;

import java.io.IOException;

import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaDawudZuhdProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaDawudZuhdProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaDawudZuhdProcessor();

		SunnahTestUtils.loadAndAssertShamelaSize("zuhd_dawud/012.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 11, null,
				"حَدَّثَنَا أَبُو دَاوُدَ قَالَ نا", "ص: 38", " إِلَى رَأْسِ صَاحِبِهِ");

		SunnahTestUtils.loadAndAssertShamelaSize("zuhd_dawud/553.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 502, null,
				"بِالْأَصَابِعِ فِي الشَّرِّ");
	}
}
