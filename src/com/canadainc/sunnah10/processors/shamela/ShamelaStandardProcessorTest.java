package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaStandardProcessorTest
{
	private ShamelaProcessor s;
	
	@Before
	public void setUp() throws Exception {
		s = new AbstractShamelaProcessor();
	}
	
	@Test
	public void processDawud() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("zuhd_dawud/012.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 11, null,
				"حَدَّثَنَا أَبُو دَاوُدَ قَالَ نا", "ص: 38", " إِلَى رَأْسِ صَاحِبِهِ");

		SunnahTestUtils.loadAndAssertSize("zuhd_dawud/553.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 502, null,
				"بِالْأَصَابِعِ فِي الشَّرِّ");
	}
	
	
	@Test
	public void processAhmad() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("zuhd_ahmad/0001.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1, null,
				"أَخْبَرَنَا الشَّيخُ الْجَلِيلُ الْعَدْلُ نَاصِرُ الدِّينِ أَبُو عَبْدِ",
				"أَبُو بَكرٍ أَحْمَدُ بْنُ جَعْفَرِ بْنِ حَمْدَانَ بْنِ مَالِكٍ الْقَطِيعِيُّ");
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 2, null,
				"حَدَّثَنَا أَبُو عَبْدِ الرَّحْمَنِ عَبْدُ اللَّهِ بْنُ أَحْمَدَ بْنِ مُحَمَّدِ",
				"الْمَسْجِدِ أَوْ رَاحَ أَعَدَّ اللَّهُ عَزَّ وَجَلَّ لَهُ فِي الْجَنَّةِ نُزُلًا كُلَّمَا غَدَا أَوْ رَاحَ");
	}
}
