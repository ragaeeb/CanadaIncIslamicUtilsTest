package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaJaamiFadlProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaJaamiFadlProcessor();
	}

	@Test
	public void breakUp() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami_fadl/0080.txt", s, 3);
		SunnahTestUtils.assertNarration(s.getNarrations().get(2), 89, null,
				"وَزَهَّدَهُ فِي الدُّنْيَا، وَبَصَّرَهُ عُيُوبَهُ", "وَقَالَ صَلَّى اللَّهُ عَلَيْهِ");
	}
	
	
	@Test
	public void breakUp2() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami_fadl/0799.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 1126, null,
				"كَانُوا يَعْلَمُونَ", "وَالْمَدِينَةُ خَيْرٌ");
	}
}