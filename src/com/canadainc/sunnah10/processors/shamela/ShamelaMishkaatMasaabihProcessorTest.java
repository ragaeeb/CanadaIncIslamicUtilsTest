package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaMishkaatMasaabihProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaMishkaatMasaabihProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("mishkaat_masaabeeh/0009.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1, "مُتَّفق عَلَيْهِ",
				"عَنْ عُمَرَ بْنِ الْخَطَّابِ رَضِيَ", "إِلَى مَا هَاجر إِلَيْهِ");
		assertFalse( s.getNarrations().get(0).text.contains("مُتَّفق عَلَيْهِ") );

		SunnahTestUtils.loadAndAssertSize("mishkaat_masaabeeh/7436.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 6294, "حسن",
				"ص: 1772", "هَذَا حَدِيثٌ حَسَنٌ");
	}
	
	
	@Test
	public void multi() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("mishkaat_masaabeeh/5155.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 4316, "مُتَّفَقٌ عَلَيْهِ",
				"وَعَنْ عُمَرَ وَأَنَسٍ وَابْنِ الزُّبَيْرِ", "يَلْبَسْهُ فِي الْآخِرَة");
		assertFalse( s.getNarrations().get(0).text.contains("4318") );
	}
}