package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class FathBaariProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new FathBaariProcessor();
	}

	@Test
	public void standard() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("fath_albari/0534.txt", s, 1);
		SunnahTestUtils.assertBody( s.getNarrations().get(0), 9, "قَوْلُهُ عَنْ أَبِي هُرَيْرَةَ",
				"أَصْحَابُ السُّنَنِ الثَّلَاثَةُ");

		SunnahTestUtils.loadAndAssertSize("fath_albari/2279.txt", s, 1);
		assertTrue( s.getNarrations().get(0).text.contains("قَدْ أُمِرَ بِتَعْذِيبِهِ") );
		assertTrue( s.getNarrations().get(0).text.contains("فِيهِ فِرَاق الأحباب") );

		// due to the title node nothing more should be caught
		assertFalse( s.getNarrations().get(0).text.contains("أَيْ مَاذَا يَصْنَعُ كَذَا ثَبَتَتِ") );
		assertFalse( s.getNarrations().get(0).text.contains("وَتَعَالَى أَعْلَمُ بِالصَّوَابِ") );

		SunnahTestUtils.loadAndAssertSize("fath_albari/2281.txt", s, 1);
		// since we still did not hit a narration this should not get picked up
		assertFalse( s.getNarrations().get(0).text.contains("وَالسَّبَبُ فِي اخْتِلَافِهِمْ") );

		SunnahTestUtils.loadAndAssertSize("fath_albari/0561.txt", s, 2);
		assertFalse( s.getNarrations().get(0).text.contains("أَيْ صُيِّرَتْ لَكُمْ إِرْثًا وَأَطْلَقَ") );
		assertFalse( s.getNarrations().get(0).text.contains("فِي إِبْهَامِ الْمُصَنِّفِ الْقَائِلِ وَاللَّهُ أَعْلَمُ") );

		SunnahTestUtils.assertBody( s.getNarrations().get(1), 26, "قَوْلُهُ حَدَّثَنَا أَحْمَدُ بْنُ يُونُسَ",
				"الَّذِي لَا يُخَالِطُهُ إِثْمٌ وَقِيلَ الَّذِي");

		// shouldn't change anything because this index is lower
		SunnahTestUtils.loadAndAssertSize("fath_albari/3050.txt", s, 2);
		SunnahTestUtils.assertBody( s.getNarrations().get(1), 26, "[2] اهويه عَنْ عَبْدِ الرَّزَّاقِ مِثْلَ",
				"بِهِ فِي ذَلِك وَالله أعلم");
	}

	@Test
	public void consecutive() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("fath_albari/0494.txt", s, 1);
		SunnahTestUtils.assertBody( s.getNarrations().get(0), 1, "قَوْلُهُ حَدَّثَنَا الْحُمَيْدِيُّ",
				"مُخلصين لَهُ الدّين");

		SunnahTestUtils.loadAndAssertSize("fath_albari/0502.txt", s, 2);
		SunnahTestUtils.assertBody( s.getNarrations().get(0), 1, "أَوْ بِطَوَافِهِ الْعِبَادَةَ وَمُلَازَمَةَ",
				"اللَّهُ تَعَالَى وَبِاللَّهِ التَّوْفِيق");
		SunnahTestUtils.assertBody( s.getNarrations().get(1), 2, "الحَدِيث الثَّانِي من أَحَادِيث",
				"وَكَانَ مِنْ فُضَلَاءِ الصَّحَابَة");
	}
}