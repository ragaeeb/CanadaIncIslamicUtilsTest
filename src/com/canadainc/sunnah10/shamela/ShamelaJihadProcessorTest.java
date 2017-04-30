package com.canadainc.sunnah10.shamela;

import java.io.IOException;

import org.junit.Test;

public class ShamelaJihadProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaJihadProcessor();

		ShamelaTestUtils.loadAndAssertSize("jihad/003.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 2, null,
				"حَدَّثَنَا أَبُو يُوسُفَ مُحَمَّدُ", "الْأَعْمَالِ أَفْضَلَ أَوْ أَحَبَّ إِلَى", "مَرْصُوصٌ} [الصف: 2]");

		ShamelaTestUtils.loadAndAssertSize("jihad/019.txt", s, 3);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 18, null,
				"فِيهَا، أَوْ مَا عَلَيْهَا");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(2), 19, null,
				"عَلَيْهِ وَسَلَّمَ نَحْوَهُ");

		ShamelaTestUtils.loadAndAssertSize("jihad/155.txt", s, 4);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(3), 20, null,
				"الزمر: 30");

		ShamelaTestUtils.loadAndAssertSize("jihad/215.txt", s, 5);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(4), 21, null,
				"وَجَلَّ خَيْرُهُمْ لِجَارِهِ");
	}
}