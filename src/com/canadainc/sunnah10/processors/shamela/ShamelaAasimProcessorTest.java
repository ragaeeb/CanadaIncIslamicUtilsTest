package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaAasimProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaAasimProcessor();
	}

	@Test
	public void ignoreTitles() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_aasim/0001.txt", s, 0);
	}

	@Test
	public void specialTitle() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_aasim/0738.txt", s, 1);
		SunnahTestUtils.assertBody(s.getNarrations().get(0), 715,
				"ثنا يَعْقُوبُ بْنُ سُفْيَانَ", "مِمَّنْ خَلَقَ اللَّهُ أَيَّ طَرَفَيْهِ");
	}

	@Test
	public void normal() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_aasim/0002.txt", s, 1);
		SunnahTestUtils.assertBody(s.getNarrations().get(0), 1,
				"أَخْبَرَنَا هِشَامُ بْنُ عَمَّارِ بْنِ", "مِنْهُ مَفْصِلٌ إِلَّا دَخَلَهُ");
	}

	@Test
	public void continued() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_aasim/1574.txt", s, 1);
		SunnahTestUtils.loadAndAssertSize("sunnah_aasim/1575.txt", s, 1);
		SunnahTestUtils.loadAndAssertSize("sunnah_aasim/1576.txt", s, 1);
		SunnahTestUtils.assertBody(s.getNarrations().get(0), 1559,
				"قَالَ أَبُو بَكْرِ بْنُ أَبِي عَاصِمٍ", "وَالرَّجَاءُ لِلْمُذْنِبِينَ، وَتَرْكُ الْوَعِيدِ",
				"بِالْمَعْرُوفِ، وَالنَّهْيِ عَنِ الْمُنْكَرِ، وَالتَّعَاونُ");
	}
}
