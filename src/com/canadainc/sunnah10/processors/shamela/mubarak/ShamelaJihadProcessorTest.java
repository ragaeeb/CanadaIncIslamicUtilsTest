package com.canadainc.sunnah10.processors.shamela.mubarak;

import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaJihadProcessorTest
{
	@Test
	public void testProcess() throws Exception
	{
		ShamelaProcessor s = new ShamelaJihadProcessor();

		SunnahTestUtils.loadAndAssertSize("jihad/003.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 2, null,
				"حَدَّثَنَا أَبُو يُوسُفَ مُحَمَّدُ", "الْأَعْمَالِ أَفْضَلَ أَوْ أَحَبَّ إِلَى", "مَرْصُوصٌ} [الصف: 2]");

		SunnahTestUtils.loadAndAssertSize("jihad/019.txt", s, 3);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 18, null,
				"فِيهَا، أَوْ مَا عَلَيْهَا");
		SunnahTestUtils.assertNarration(s.getNarrations().get(2), 19, null,
				"عَلَيْهِ وَسَلَّمَ نَحْوَهُ");

		SunnahTestUtils.loadAndAssertSize("jihad/155.txt", s, 4);
		SunnahTestUtils.assertNarration(s.getNarrations().get(3), 20, null,
				"الزمر: 30");

		SunnahTestUtils.loadAndAssertSize("jihad/215.txt", s, 5);
		SunnahTestUtils.assertNarration(s.getNarrations().get(4), 21, null,
				"وَجَلَّ خَيْرُهُمْ لِجَارِهِ");
	}
}