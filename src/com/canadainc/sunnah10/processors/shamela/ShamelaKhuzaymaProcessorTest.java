package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaKhuzaymaProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaKhuzaymaProcessor();
	}

	@Test
	public void readNormal() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("saheeh_khuzayma/0824.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 493, null,
				"نا مُحَمَّدُ بْنُ إِسْحَاقَ الصَّغَانِيُّ", "الفاتحة: 5] وَجَمَعَ خَمْسَ أَصَابِعِهِ");
	}
	
	@Test
	public void readChapter() throws Exception {
		SunnahTestUtils.loadAndAssertSize("saheeh_khuzayma/0825.txt", s, 0);
	}
	
	@Test
	public void readTaleeq() throws Exception {
		SunnahTestUtils.loadAndAssertSize("saheeh_khuzayma/0006.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 2, null,
				"حَدَّثَنَا مُحَمَّدُ بْنُ بَشَّارٍ، ثنا يَحْيَى", "لَفْظُ حَدِيثِ يَحْيَى بْنِ سَعِيدٍ");
	}
	
	@Test
	public void readDouble() throws Exception {
		SunnahTestUtils.loadAndAssertSize("saheeh_khuzayma/0061.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 33, null,
				"ثنا مُحَمَّدُ بْنُ الْعَلَاءِ بْنِ كُرَيْبٍ الْهَمْدَانِيُّ", "الذَّكَرِ، فَقَالَ: «أَسْتَحِبُّهُ وَلَا أُوجِبُهُ");
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 34, null,
				"وَسَمِعْتُ مُحَمَّدَ بْنَ يَحْيَى", "عُلَمَائِنَا أَنَّ الْخَبَرَ وَاهٍ لِطَعْنِهِ فِي مَرْوَانَ");
	}
	
	@Test
	public void readBlanked() throws Exception {
		SunnahTestUtils.loadAndAssertSize("saheeh_khuzayma/1703.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1069, null,
				"ثنا بُنْدَارٌ، نا عَبْدُ اللَّهِ بْنُ حُمْرَانَ", "أَنَّ الْفَرْضَ مِنَ الصَّلَاةِ خَمْسٌ لَا سِتٌّ");
	}
}
