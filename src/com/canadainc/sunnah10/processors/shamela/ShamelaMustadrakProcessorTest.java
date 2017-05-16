package com.canadainc.sunnah10.processors.shamela;

import java.io.IOException;

import org.junit.Test;

import com.canadainc.sunnah10.processors.shamela.ShamelaMustadrakProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaMustadrakProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaMustadrakProcessor();

		ShamelaTestUtils.loadAndAssertSize("mustadrak/0004.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 2, null, "حَدَّثَنَاهُ عَلِيُّ بْنُ حَمْشَاذَ الْعَدْلُ", "بِمُحَمَّدِ بْنِ عَجْلَانَ «وَقَدْ رُوِيَ ", "قِلَابَةَ لَمْ يَسْمَعْهُ، عَنْ عَائِشَةَ");

		// 2 in 1
		ShamelaTestUtils.loadAndAssertSize("mustadrak/0081.txt", s, 3);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 78, "صحيح الإسناد سمع الحسن من عمران",
				"أَخْبَرَنَا أَبُو جَعْفَرٍ أَحْمَدُ", "ص: 82");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(2), 79, "وقد أخرجا حديث الأعمش عن أبي صالح عن أبي سعيد",
				"عَنْ أَبِي سَعِيدٍ بَعْضِ هَذَا الْمَتْنِ");
		
		// 2 in 1, with black 2nd narration
		ShamelaTestUtils.loadAndAssertSize("mustadrak/0169.txt", s, 5);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(4), 175, null, "وَيَحْيَى كَثِيرُ الْوَهْمِ عَلَى أَبِيهِ");
	}
}