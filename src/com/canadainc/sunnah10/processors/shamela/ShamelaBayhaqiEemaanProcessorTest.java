package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaBayhaqiEemaanProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaBayhaqiEemaanProcessor();
	}

	@Test
	public void typo() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("bayhaqi_shuab_eemaan/01460.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 1284, null,
				"أَخْبَرَنَا أَبُو عَبْدِ اللهِ الْحَافِظُ", "عَنِ ابْنِ عَوْنٍ، مُرْسَلًا");
	}

	@Test
	public void highlight() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("bayhaqi_shuab_eemaan/05893.txt", s, 4);
		SunnahTestUtils.assertNarration(s.getNarrations().get(2), 5431, null,
				"أَخْبَرَنَاهُ أَبُو عَبْدِ اللهِ الْحَافِظُ", "النَّبِيِّ صَلَّى اللَّهُ عَلَيْهِ وَسَلَّمَ، نَحْوَهُ");
		SunnahTestUtils.assertNarration(s.getNarrations().get(3), 5431, null,
				"أَخْبَرَنَاهُ مُحَمَّدُ بْنُ عَبْدِ اللهِ", "ثَنَا الْأَعْمَشُ، فَذَكَرَهُ");
	}


	@Test
	public void fixTypo() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("bayhaqi_shuab_eemaan/08615.txt", s, 3);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 8061, null,
				"أَخْبَرَنَا أَبُو عُثْمَانَ سَعِيدُ", "الثَّوْرِيُّ، عَنْ قَابُوسٍ مَوْقُوفًا");
		SunnahTestUtils.assertNarration(s.getNarrations().get(2), 8063, null,
				"قَالَ: وَأنا ابْنُ أَبِي", "النَّبِيِّ صَلَّى اللَّهُ عَلَيْهِ وَسَلَّمَ بِمِثْلِهِ");
	}
}
